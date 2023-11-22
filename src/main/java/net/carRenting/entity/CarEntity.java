package net.carRenting.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String brand;

    @NotNull
    @Size(max = 50)
    private String model;

    @NotNull
    private Integer year;

    @NotNull
    private boolean available = false;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "car", fetch = jakarta.persistence.FetchType.LAZY)
    private List<RentalEntity> rentals;

    public CarEntity() {
        rentals = new ArrayList<>();
    }
    
    public CarEntity(String brand, String model, Integer year, boolean available) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = available;
    }

    public CarEntity(Long id, String brand, String model, Integer year, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = available;
    }

    public CarEntity(String brand, String model, Integer year, boolean available, UserEntity user) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = available;
        this.user = user;
    }

    // public CarEntity(String brand, String model, Integer year, boolean available, UserEntity user, RentalEntity rental) {
    //     this.brand = brand;
    //     this.model = model;
    //     this.year = year;
    //     this.available = available;
    //     this.user = user;
    //     this.rental = rental;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getRentals() {
        return this.rentals.size();
    }

}

