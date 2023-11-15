package net.carRenting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Size(max = 20)
    private String transmission;

    @NotNull
    @Size(max = 20)
    private String fuel;
    
    @NotNull
    private Integer doors;

    @NotNull
    private Integer seats;
    
    @NotNull
    @Size(max = 20)
    private String color;

    @NotNull
    private Integer hp;

    @NotNull
    @Size(max = 255)
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_rental")
    private RentalEntity rental;

    public CarEntity() {
    }

    public CarEntity(String brand, String model, Integer year, String transmission, String fuel, Integer doors, Integer seats, String color, Integer hp, String image) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.transmission = transmission;
        this.fuel = fuel;
        this.doors = doors;
        this.seats = seats;
        this.color = color;
        this.hp = hp;
        this.image = image;
    }

    public CarEntity(Long id, String brand, String model, Integer year, String transmission, String fuel, Integer doors, Integer seats, String color, Integer hp, String image) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.transmission = transmission;
        this.fuel = fuel;
        this.doors = doors;
        this.seats = seats;
        this.color = color;
        this.hp = hp;
        this.image = image;
    }

    public CarEntity(String brand, String model, Integer year, String transmission, String fuel, Integer doors, Integer seats, String color, Integer hp, String image, UserEntity user, RentalEntity rental) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.transmission = transmission;
        this.fuel = fuel;
        this.doors = doors;
        this.seats = seats;
        this.color = color;
        this.hp = hp;
        this.image = image;
        this.user = user;
        this.rental = rental;
    }

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

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHorsePower() {
        return hp;
    }

    public void setHorsePower(Integer hp) {
        this.hp = hp;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }

}

