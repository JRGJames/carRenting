package net.carRenting.service;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import net.carRenting.bean.CostumerBean;
import net.carRenting.entity.CostumerEntity;
import net.carRenting.exception.UnauthorizedException;
import net.carRenting.helper.JWTHelper;
import net.carRenting.repository.CostumerRepository;

public class SessionService {

    @Autowired
    CostumerRepository oCostumerRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public String login(CostumerBean oCostumerBean) {
        oCostumerRepository.findByUsernameAndPassword(oCostumerBean.getUsername(), oCostumerBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong User or password"));
        return JWTHelper.generateJWT(oCostumerBean.getUsername());
    }

    public String getSessionUsername() {        
        if (oHttpServletRequest.getAttribute("username") instanceof String) {
            return oHttpServletRequest.getAttribute("username").toString();
        } else {
            return null;
        }
    }

    public CostumerEntity getSessionCostumer() {
        if (this.getSessionUsername() != null) {
            return oCostumerRepository.findByUsername(this.getSessionUsername()).orElse(null);    
        } else {
            return null;
        }
    }

    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return oCostumerRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            CostumerEntity oUserEntityInSession = oCostumerRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.FALSE.equals(oUserEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public Boolean isCostumer() {
        if (this.getSessionUsername() != null) {
            CostumerEntity oUserEntityInSession = oCostumerRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.TRUE.equals(oUserEntityInSession.getRole());
        } else {
            return false;
        }
    }

    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new UnauthorizedException("Only admins can do this");
        }
    }

    public void onlyCostumers() {
        if (!this.isCostumer()) {
            throw new UnauthorizedException("Only costumers can do this");
        }
    }

    public void onlyAdminsOrUsers() {
        if (!this.isSessionActive()) {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

    public void onlyCostumersWithIisOwnData(Long id_costumer) {
        if (!this.isCostumer()) {
            throw new UnauthorizedException("Only costumers can do this");
        }
        if (!this.getSessionUser().getId().equals(id_costumer)) {
            throw new UnauthorizedException("Only costumers can do this");
        }
    }

    public void onlyAdminsOrUsersWithIisOwnData(Long id_user) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isCostumer()) {
                    throw new UnauthorizedException("Only admins or costumers can do this");
                } else {
                    if (!this.getSessionCostumer().getId().equals(id_user)) {
                        throw new UnauthorizedException("Only admins or costumers with its own data can do this");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

}