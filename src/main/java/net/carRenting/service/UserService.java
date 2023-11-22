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

    private final String carRentingPASSWORD = "05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e";


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
        return userRepository.findUsersByRentalsNumberDescFilter(pageable);
    }

    public Long create(UserEntity userEntity) {
        sessionService.onlyAdmins();
        userEntity.setId(null);
        userEntity.setPassword(carRentingPASSWORD);
        return userRepository.save(userEntity).getId();
    }

    public UserEntity update(UserEntity userEntityToSet) {
        UserEntity userEntityFromDatabase = this.get(userEntityToSet.getId());
        sessionService.onlyAdminsOrUsersWithIisOwnData(userEntityFromDatabase.getId());
        if (sessionService.isUser()) {
            userEntityToSet.setRole(userEntityFromDatabase.getRole());
            userEntityToSet.setPassword(carRentingPASSWORD);
            return userRepository.save(userEntityToSet);
        } else {
            userEntityToSet.setPassword(carRentingPASSWORD);
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
            String email = DataGenerationHelper.getRandomEmail();
            String username = DataGenerationHelper.getRandomUsername();
            userRepository.save(new UserEntity(name, surname, email, username,
                    carRentingPASSWORD, false));
        }
        return userRepository.count();
    }

    @Transactional
    public Long empty() {
        sessionService.onlyAdmins();
        userRepository.deleteAll(); 
        userRepository.resetAutoIncrement();
        UserEntity userEntity1 = new UserEntity("Carlos", "Sainz", "cs@gmail.com", "carlossainz55", carRentingPASSWORD, false);
        userRepository.save(userEntity1);
        userEntity1 = new UserEntity("Fernando", "Alonso", "fa@gmail.com", "fernandoalo", carRentingPASSWORD, true);
        userRepository.save(userEntity1);
        return userRepository.count();
    }
}