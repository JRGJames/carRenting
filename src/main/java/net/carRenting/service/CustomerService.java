package net.carRenting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.entity.CustomerEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.helper.DataGenerationHelper;
import net.carRenting.repository.CustomerRepository;
import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final String carrentingPASSWORD = "05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e";

    String memberSinceString = "2012-04-23";
    LocalDateTime memberSince = LocalDateTime.parse(memberSinceString);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    SessionService sessionService;

    public CustomerEntity get(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    public CustomerEntity getByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found by username"));
    }

    public Page<CustomerEntity> getPage(org.springframework.data.domain.Pageable pageable) {
        sessionService.onlyAdmins();
        return customerRepository.findAll(pageable);
    }

    public Page<CustomerEntity> getPageByCarsNumberDesc(Pageable pageable) {
        return customerRepository.findCustomersByCarsNumberDescFilter(pageable);
    }

    public Long create(CustomerEntity customerEntity) {
        sessionService.onlyAdmins();
        customerEntity.setId(null);
        customerEntity.setPassword(carrentingPASSWORD);
        return customerRepository.save(customerEntity).getId();
    }

    public CustomerEntity update(CustomerEntity customerEntityToSet) {
        CustomerEntity customerEntityFromDatabase = this.get(customerEntityToSet.getId());
        sessionService.onlyAdminsOrCustomersWithIisOwnData(customerEntityFromDatabase.getId());
        if (sessionService.isCustomer()) {
            customerEntityToSet.setId(null);
            customerEntityToSet.setRole(customerEntityFromDatabase.getRole());
            customerEntityToSet.setPassword(carrentingPASSWORD);
            return customerRepository.save(customerEntityToSet);
        } else {
            customerEntityToSet.setId(null);
            customerEntityToSet.setPassword(carrentingPASSWORD);
            return customerRepository.save(customerEntityToSet);
        }
    }

    public Long delete(Long id) {
        sessionService.onlyAdmins();
        customerRepository.deleteById(id);
        return id;
    }

    public CustomerEntity getOneRandom() {
        sessionService.onlyAdmins();
        Pageable pageable = PageRequest.of((int) (Math.random() * customerRepository.count()), 1);
        return customerRepository.findAll(pageable).getContent().get(0);
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
            LocalDateTime memberSince = DataGenerationHelper.getRadomDate();
            String username = DataGenerationHelper.getRandomUsername();
            customerRepository.save(new CustomerEntity(name, surname, phoneNumber, email, address, city, province,
                    postalCode, country, memberSince, username,
                    "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true));
        }
        return customerRepository.count();
    }

    @Transactional
    public Long empty() {
        sessionService.onlyAdmins();
        customerRepository.deleteAll();
        customerRepository.resetAutoIncrement();
        CustomerEntity customerEntity1 = new CustomerEntity("Carlos", "Sainz", "55555555", "cs@gmail.com", "C/Carlos Sainz", "Madrid", "Madrid", "43055", "Spain", memberSince, "carlossainz55", "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true );
        customerRepository.save(customerEntity1);
        customerEntity1 = new CustomerEntity("Fernando", "Alonso", "333333333", "fa@gmail.com", "C/Fernando Alonso", "Asturias", "Oviedo", "43033", "Spain", memberSince , "fernandoalo_oficial", "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true );
        customerRepository.save(customerEntity1);
        return customerRepository.count();
    }
}