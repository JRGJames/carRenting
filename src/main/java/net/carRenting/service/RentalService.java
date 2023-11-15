package net.carRenting.service;

import java.time.LocalDateTime;
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
import net.carRenting.repository.UserRepository;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    public RentalEntity get(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental not found"));
    }

    public Page<RentalEntity> getPage(Pageable pageable, Long userId) {
        if (userId == 0) {
            return rentalRepository.findAll(pageable);
        } else {
            return rentalRepository.findByUserId(userId, pageable);
        }
    }

    public Page<RentalEntity> getPageByCarsNumberDesc(Pageable pageable, Long userId) {
        if (userId == 0) {
            return rentalRepository.findRentalsByCarsNumberDesc(pageable);
        } else {
            return rentalRepository.findRentalsByCarsNumberDescFilterByUserId(userId, pageable);
        }
    }


    public Long create(RentalEntity rentalEntity) {
        rentalEntity.setId(null);
        sessionService.onlyAdminsOrUsers();
        if (sessionService.isUser()) {
            rentalEntity.setUser(sessionService.getSessionUser());
            return rentalRepository.save(rentalEntity).getId();
        } else {
            return rentalRepository.save(rentalEntity).getId();
        }
    }

    public RentalEntity update(RentalEntity rentalEntityToSet) {
        RentalEntity rentalEntityFromDatabase = this.get(rentalEntityToSet.getId());
        sessionService.onlyAdminsOrUsersWithIisOwnData(rentalEntityFromDatabase.getUser().getId());
        if (sessionService.isUser()) {
            if (rentalEntityToSet.getUser().getId().equals(sessionService.getSessionUser().getId())) {
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
        sessionService.onlyAdminsOrUsersWithIisOwnData(rentalEntityFromDatabase.getUser().getId());
        rentalRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        sessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            LocalDateTime pickupDate = DataGenerationHelper.getRandomPickupDate();
            LocalDateTime dropoffDate = DataGenerationHelper.getRandomDropoffDate(pickupDate);
            String pickupLocation = DataGenerationHelper.getRandomPickupLocation();
            String dropoffLocation = DataGenerationHelper.getRandomDropoffLocation();
            Float cost = DataGenerationHelper.getRandomCost();
            rentalRepository
                    .save(new RentalEntity(pickupDate, dropoffDate, pickupLocation, dropoffLocation, cost, userService.getOneRandom()));
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