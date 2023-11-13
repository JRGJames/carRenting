package net.carRenting.service;

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
    RentalRepository oRentalRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    CustomerRepository oCustomerRepository;

    @Autowired
    CustomerService oCustomerService;

    @Autowired
    SessionService oSessionService;

    public RentalEntity get(Long id) {
        return oRentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental not found"));
    }

    public Page<RentalEntity> getPage(Pageable oPageable, Long customerId) {
        if (customerId == 0) {
            return oRentalRepository.findAll(oPageable);
        } else {
            return oRentalRepository.findByCustomerId(customerId, oPageable);
        }
    }

    public Page<RentalEntity> getPageByRentalsNumberDesc(Pageable oPageable, Long customerId) {
        if (customerId == 0) {
            return oRentalRepository.findRentalsByCarsNumberDesc(oPageable);
        } else {
            return oRentalRepository.findRentalsByCarsNumberDescFilterByCustomerId(customerId, oPageable);
        }
    }


    public Long create(RentalEntity oRentalEntity) {
        oRentalEntity.setId(null);
        oSessionService.onlyAdminsOrCustomers();
        if (oSessionService.isCustomer()) {
            oRentalEntity.setCustomer(oSessionService.getSessionCustomer());
            return oRentalRepository.save(oRentalEntity).getId();
        } else {
            return oRentalRepository.save(oRentalEntity).getId();
        }
    }

    public RentalEntity update(RentalEntity oRentalEntityToSet) {
        RentalEntity oRentalEntityFromDatabase = this.get(oRentalEntityToSet.getId());
        oSessionService.onlyAdminsOrCustomersWithIisOwnData(oRentalEntityFromDatabase.getCustomer().getId());
        if (oSessionService.isCustomer()) {
            if (oRentalEntityToSet.getCustomer().getId().equals(oSessionService.getSessionCustomer().getId())) {
                return oRentalRepository.save(oRentalEntityToSet);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oRentalRepository.save(oRentalEntityToSet);
        }
    }

    public Long delete(Long id) {
        RentalEntity oRentalEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrCustomersWithIisOwnData(oRentalEntityFromDatabase.getCustomer().getId());
        oRentalRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            oRentalRepository
                    .save(new RentalEntity(DataGenerationHelper.getSpeech(1), oCustomerService.getOneRandom()));
        }
        return oRentalRepository.count();
    }

    public RentalEntity getOneRandom() {oRentalRepository
        oSessionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oRentalRepository.count()), 1);
        return oRentalRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oRentalRepository.deleteAll();
        oRentalRepository.resetAutoIncrement();
        oRentalRepository.flush();
        return oRentalRepository.count();
    }

}