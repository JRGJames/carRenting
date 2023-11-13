package net.carRenting.service;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.bean.CustomerBean;
import net.carRenting.entity.CustomerEntity;
import net.carRenting.exception.ResourceNotFoundException;
import net.carRenting.exception.UnauthorizedException;
import net.carRenting.helper.JWTHelper;
import net.carRenting.repository.CustomerRepository;

public class SessionService {

    @Autowired
    CustomerRepository oCustomerRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public String login(CustomerBean oCustomerBean) {
        oCustomerRepository.findByUsernameAndPassword(oCustomerBean.getUsername(), oCustomerBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Customer or password"));
        return JWTHelper.generateJWT(oCustomerBean.getUsername());
    }

    public String getSessionUsername() {        
        if (oHttpServletRequest.getAttribute("username") instanceof String) {
            return oHttpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    public CustomerEntity getSessionCustomer() {
        if (this.getSessionUsername() != null) {
            return oCustomerRepository.findByUsername(this.getSessionUsername()).orElse(null);    
        } else {
            return null;
        }
    }

    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return oCustomerRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            CustomerEntity oCustomerEntityInSession = oCustomerRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            return Boolean.FALSE.equals(oCustomerEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public Boolean isCustomer() {
        if (this.getSessionUsername() != null) {
            CustomerEntity oCustomerEntityInSession = oCustomerRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            return Boolean.TRUE.equals(oCustomerEntityInSession.getRole());
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

    public void onlyAdminsOrCustomersWithIisOwnData(Long id_user) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isCustomer()) {
                    throw new UnauthorizedException("Only admins or customers can do this");
                } else {
                    if (!this.getSessionCustomer().getId().equals(id_user)) {
                        throw new UnauthorizedException("Only admins or costumers with its own data can do this");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

}