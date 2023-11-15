package net.carRenting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.bean.CustomerBean;
import net.carRenting.entity.CustomerEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.exception.UnauthorizedException;
import net.carRenting.helper.JWTHelper;
import net.carRenting.repository.CustomerRepository;

@Service
public class SessionService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    public String login(CustomerBean customerBean) {
        customerRepository.findByUsernameAndPassword(customerBean.getUsername(), customerBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Customer or password"));
        return JWTHelper.generateJWT(customerBean.getUsername());
    }

    public String getSessionUsername() {        
        if (httpServletRequest.getAttribute("username") instanceof String) {
            return httpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    public CustomerEntity getSessionCustomer() {
        if (this.getSessionUsername() != null) {
            return customerRepository.findByUsername(this.getSessionUsername()).orElse(null);    
        } else {
            return null;
        }
    }

    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return customerRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            CustomerEntity customerEntityInSession = customerRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            return Boolean.FALSE.equals(customerEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public Boolean isCustomer() {
        if (this.getSessionUsername() != null) {
            CustomerEntity customerEntityInSession = customerRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            return Boolean.TRUE.equals(customerEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new UnauthorizedException("Only admins can do this");
        }
    }

    public void onlyCustomers() {
        if (!this.isCustomer()) {
            throw new UnauthorizedException("Only customers can do this");
        }
    }

    public void onlyAdminsOrCustomers() {
        if (!this.isSessionActive()) {
            throw new UnauthorizedException("Only admins or customers can do this");
        }
    }

    public void onlyCustomersWithIisOwnData(Long id_customer) {
        if (!this.isCustomer()) {
            throw new UnauthorizedException("Only customers can do this");
        }
        if (!this.getSessionCustomer().getId().equals(id_customer)) {
            throw new UnauthorizedException("Only customers can do this");
        }
    }

    public void onlyAdminsOrCustomersWithIisOwnData(Long id_custumer) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isCustomer()) {
                    throw new UnauthorizedException("Only admins or customers can do this");
                } else {
                    if (!this.getSessionCustomer().getId().equals(id_custumer)) {
                        throw new UnauthorizedException("Only admins or costumers with its own data can do this");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("Only admins or customers can do this");
        }
    }

}