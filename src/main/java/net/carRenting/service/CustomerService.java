package net.carRenting.service;

import java.sql.Date;

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
import net.carRenting.repository.RentalRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository oCustomerRepository;

    @Autowired
    RentalRepository oRentalRepository;

    @Autowired
    SessionService oSessionService;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public CustomerEntity get(Long id) {
        return oCustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    public Page<CustomerEntity> getPage(org.springframework.data.domain.Pageable oPageable) {
        return oCustomerRepository.findAll(oPageable);
    }

    public Long create(CustomerEntity oCustomerEntity) {
        oCustomerEntity.setId(null);
        return oCustomerRepository.save(oCustomerEntity).getId();
    }

    public CustomerEntity update(CustomerEntity oCustomerEntityToSet) {

        //Añadir logica


        CustomerEntity oCustomerEntityFromDatabase = this.get(oCustomerEntityToSet.getId());
        // Aquí puedes agregar la lógica de actualización según tus requisitos.
        // Por ejemplo, copiar los campos del objeto recibido al objeto existente.
        oCustomerEntityFromDatabase.setFirstName(oCustomerEntityToSet.getFirstName());
        oCustomerEntityFromDatabase.setLastName(oCustomerEntityToSet.getLastName());
        // ... (haz lo mismo para los demás campos)
        return oCustomerRepository.save(oCustomerEntityFromDatabase);
    }

    public Long delete(Long id) {
        oCustomerRepository.deleteById(id);
        return id;
    }

    public CustomerEntity getOneRandom() {
        oSessionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oCustomerRepository.count()), 1);
        return oCustomerRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
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
            Date memberSince = DataGenerationHelper.getCurrentSqlDate();
            String username = DataGenerationHelper.getRandomUsername();
            oCustomerRepository.save(new CustomerEntity(name, surname, phoneNumber, email, address, city, province, postalCode, country, memberSince, username, "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true ));
        }
        return oCustomerRepository.count();
    }

    @Transactional
    public Long empty() {

        //Añadir lógica


        oCustomerRepository.deleteAll();
        // Puedes agregar lógica adicional aquí según tus requisitos.
        return oCustomerRepository.count();
    }
}
