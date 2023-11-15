package net.carRenting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.entity.UserEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.helper.DataGenerationHelper;
import net.carRenting.repository.UserRepository;

@Service
public class UserService {

    private final String carrentingPASSWORD = "05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e";


    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    SessionService sessionService;

    public UserEntity get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }

    public Page<UserEntity> getPage(Pageable pageable) {
        sessionService.onlyAdmins();
        return userRepository.findAll(pageable);
    }

    public Page<UserEntity> getPageByCarsNumberDesc(Pageable pageable) {
        return userRepository.findUsersByCarsNumberDescFilter(pageable);
    }

    public Long create(UserEntity userEntity) {
        sessionService.onlyAdmins();
        userEntity.setId(null);
        userEntity.setPassword(carrentingPASSWORD);
        return userRepository.save(userEntity).getId();
    }

    public UserEntity update(UserEntity userEntityToSet) {
        UserEntity userEntityFromDatabase = this.get(userEntityToSet.getId());
        sessionService.onlyAdminsOrUsersWithIisOwnData(userEntityFromDatabase.getId());
        if (sessionService.isUser()) {
            userEntityToSet.setId(null);
            userEntityToSet.setRole(userEntityFromDatabase.getRole());
            userEntityToSet.setPassword(carrentingPASSWORD);
            return userRepository.save(userEntityToSet);
        } else {
            userEntityToSet.setId(null);
            userEntityToSet.setPassword(carrentingPASSWORD);
            return userRepository.save(userEntityToSet);
        }
    }

    public Long delete(Long id) {
        sessionService.onlyAdmins();
        userRepository.deleteById(id);
        return id;
    }

    public UserEntity getOneRandom() {
        sessionService.onlyAdmins();
        Pageable pageable = PageRequest.of((int) (Math.random() * userRepository.count()), 1);
        return userRepository.findAll(pageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        sessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String name = DataGenerationHelper.getRandomName();
            String surname = DataGenerationHelper.getRandomSurname();
            String phoneNumber = DataGenerationHelper.getRandomPhoneNumber();
            String email = DataGenerationHelper.getRandomEmail();
            String address = DataGenerationHelper.getRandomAddress();
            String city = DataGenerationHelper.getRandomCity();
            String province = DataGenerationHelper.getRandomProvince();
            String postalCode = DataGenerationHelper.getRandomPostalCode();
            String country = DataGenerationHelper.getRandomCountry();
            String username = DataGenerationHelper.getRandomUsername();
            userRepository.save(new UserEntity(name, surname, phoneNumber, email, address, city, province,
                    postalCode, country, username,
                    "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true));
        }
        return userRepository.count();
    }

    @Transactional
    public Long empty() {
        sessionService.onlyAdmins();
        userRepository.deleteAll();
        userRepository.resetAutoIncrement();
        UserEntity userEntity1 = new UserEntity("Carlos", "Sainz", "55555555", "cs@gmail.com", "C/Carlos Sainz", "Madrid", "Madrid", "43055", "Spain", "carlossainz55", "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true );
        userRepository.save(userEntity1);
        userEntity1 = new UserEntity("Fernando", "Alonso", "333333333", "fa@gmail.com", "C/Fernando Alonso", "Asturias", "Oviedo", "43033", "Spain", "fernandoalo_oficial", "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true );
        userRepository.save(userEntity1);
        return userRepository.count();
    }
}