package net.carRenting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.entity.CarEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.helper.DataGenerationHelper;
import net.carRenting.repository.CarRepository;
import net.carRenting.repository.UserRepository;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    RentalService rentalService;

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    public CarEntity get(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
    }

    public Page<CarEntity> getPage(Pageable pageable, Long userId, Long rentalId) {
        if (userId == 0) {
            if (rentalId == 0) {
                return carRepository.findAll(pageable);
            } else {
                return carRepository.findByRentalId(rentalId, pageable);
            }
        } else {
            return carRepository.findByUserId(userId, pageable);
        }
    }

    public Long create(CarEntity carEntity) {
        sessionService.onlyAdminsOrUsers();
        carEntity.setId(null);
        if (sessionService.isUser()) {
            carEntity.setUser(sessionService.getSessionUser());
            return carRepository.save(carEntity).getId();
        } else {
            return carRepository.save(carEntity).getId();
        }
    }

    public CarEntity update(CarEntity carEntityToUpdate) {
        CarEntity carEntityFromDatabase = this.get(carEntityToUpdate.getId());
        sessionService.onlyAdminsOrUsersWithIisOwnData(carEntityFromDatabase.getUser().getId());
        if (sessionService.isUser()) {
            carEntityToUpdate.setUser(sessionService.getSessionUser());
            return carRepository.save(carEntityToUpdate);
        } else {
            return carRepository.save(carEntityToUpdate);
        }
    }

    public Long delete(Long id) {
        CarEntity carEntityFromDatabase = this.get(id);
        sessionService.onlyAdminsOrUsersWithIisOwnData(carEntityFromDatabase.getUser().getId());
        carRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        sessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String brand = DataGenerationHelper.getRandomCarBrand();
            String model = DataGenerationHelper.getRandomCarModel();
            Integer year = DataGenerationHelper.getRandomCarYear();
            String transmission = DataGenerationHelper.getRandomTransmission();
            String fuel = DataGenerationHelper.getRandomFuel();
            Integer doors = DataGenerationHelper.getRandomDoors();
            Integer seats = DataGenerationHelper.getRandomSeats();
            String color = DataGenerationHelper.getRandomColor();
            Integer hp = DataGenerationHelper.getRandomHorsePower();
            String image = DataGenerationHelper.getRandomImage();
            
            carRepository.save(new CarEntity(brand, model, year, transmission, fuel, doors, seats, color, hp, image, userService.getOneRandom(), rentalService.getOneRandom()));
        }
        return carRepository.count();
    }

    @Transactional
    public Long empty() {
        sessionService.onlyAdmins();
        carRepository.deleteAll();
        carRepository.resetAutoIncrement();
        carRepository.flush();
        return carRepository.count();
    }
}
