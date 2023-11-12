package net.carRenting.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import net.carRenting.entity.CarEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.helper.DataGenerationHelper;
import net.carRenting.repository.CarRepository;
import net.carRenting.repository.CustomerRepository;

public class CarService {
    @Autowired
    CarRepository oCarRepository;

    @Autowired
    CustomerRepository oCostumerRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    RentalService oRentalService;

    @Autowired
    CustomerService oCostumerService;

    @Autowired
    SessionService oSessionService;

    public CarRepository get(Long id) {
        return oCarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
    }

    public Page<CarEntity> getPage(Pageable oPageable, Long costumerId, Long rentalId) {
        if (costumerId == 0) {
            if (rentalId == 0) {
                return oCarRepository.findAll(oPageable);
            } else {
                return oCarRepository.findByRentalId(rentalId, oPageable);
            }
        } else {
            return oCarRepository.findByCostumerId(costumerId, oPageable);
        }
    }

    public Long create(CarEntity oCarEntity) {
        oSessionService.onlyAdminsOrCostumers();
        oCarEntity.setId(null);        
        if (oSessionService.isCostumer()) {
            oCarEntity.setCostumer(oSessionService.getSessionCostumer());
            return oCarRepository.save(oCarEntity).getId();
        } else {
            return oCarRepository.save(oCarEntity).getId();
        }
    }

    public CarEntity update(CarEntity oCarEntityToSet) {
        CarEntity oCarEntityFromDatabase = this.get(oCarEntityToSet.getId());
        oSessionService.onlyAdminsOrCostumersWithIisOwnData(oCarEntityFromDatabase.getCostumer().getId());
        if (oSessionService.isCostumer()) {
            oCarEntityToSet.setCostumer(oSessionService.getSessionCostumer());
            return oCarRepository.save(oCarEntityToSet);
        } else {
            return oCarRepository.save(oCarEntityToSet);
        }
    }

    public Long delete(Long id) {
        CarEntity oCarEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrCostumersWithIisOwnData(oCarEntityFromDatabase.getCostumer().getId());
        oCarRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            oCarRepository.save(new CarEntity(DataGenerationHelper.getSpeech(1),
                    DataGenerationHelper.getSpeech(RentalLocalRandom.current().nextInt(5, 25)),
                    oCostumerService.getOneRandom(), oRentalService.getOneRandom()));
        }
        return oCarRepository.count();
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oCarRepository.deleteAll();
        oCarRepository.resetAutoIncrement();
        oCarRepository.flush();
        return oCarRepository.count();
    }
}
