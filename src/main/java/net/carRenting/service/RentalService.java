package net.carRenting.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.entity.RentalEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.helper.DataGenerationHelper;
import net.carRenting.repository.RentalRepository;
import net.carRenting.repository.CustomerRepository;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CarService carService;

    @Autowired
    SessionService sessionService;

    public RentalEntity get(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental not found"));
    }

    public Page<RentalEntity> getPage(Pageable pageable, Long customerId) {
        if (customerId == 0) {
            return rentalRepository.findAll(pageable);
        } else {
            return rentalRepository.findByCustomerId(customerId, pageable);
        }
    }

    public Page<RentalEntity> getPageByCarsNumberDesc(Pageable pageable, Long customerId) {
        if (customerId == 0) {
            return rentalRepository.findRentalsByCarsNumberDesc(pageable);
        } else {
            return rentalRepository.findRentalsByCarsNumberDescFilterByCustomerId(customerId, pageable);
        }
    }


    public Long create(RentalEntity rentalEntity) {
        rentalEntity.setId(null);
        sessionService.onlyAdminsOrCustomers();
        if (sessionService.isCustomer()) {
            rentalEntity.setCustomer(sessionService.getSessionCustomer());
            return rentalRepository.save(rentalEntity).getId();
        } else {
            return rentalRepository.save(rentalEntity).getId();
        }
    }

    public RentalEntity update(RentalEntity rentalEntityToSet) {
        RentalEntity rentalEntityFromDatabase = this.get(rentalEntityToSet.getId());
        sessionService.onlyAdminsOrCustomersWithIisOwnData(rentalEntityFromDatabase.getCustomer().getId());
        if (sessionService.isCustomer()) {
            if (rentalEntityToSet.getCustomer().getId().equals(sessionService.getSessionCustomer().getId())) {
                return rentalRepository.save(rentalEntityToSet);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return rentalRepository.save(rentalEntityToSet);
        }
    }

    public Long delete(Long id) {
        RentalEntity rentalEntityFromDatabase = this.get(id);
        sessionService.onlyAdminsOrCustomersWithIisOwnData(rentalEntityFromDatabase.getCustomer().getId());
        rentalRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        sessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            Date pickupDate = DataGenerationHelper.getRandomPickupDate();
            Date dropoffDate = DataGenerationHelper.getRandomDropoffDate(pickupDate);
            String pickupLocation = DataGenerationHelper.getRandomPickupLocation();
            String dropoffLocation = DataGenerationHelper.getRandomDropoffLocation();
            Float cost = DataGenerationHelper.getRandomCost();
            rentalRepository
                    .save(new RentalEntity(pickupDate, dropoffDate, pickupLocation, dropoffLocation, cost, customerService.getOneRandom(), carService.getOneRandom()));
        }
        return rentalRepository.count();
    }

    public RentalEntity getOneRandom() {
        sessionService.onlyAdmins();
        Pageable pageable = PageRequest.of((int) (Math.random() * rentalRepository.count()), 1);
        return rentalRepository.findAll(pageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        sessionService.onlyAdmins();
        rentalRepository.deleteAll();
        rentalRepository.resetAutoIncrement();
        rentalRepository.flush();
        return rentalRepository.count();
    }

}