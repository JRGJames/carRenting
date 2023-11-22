package net.carRenting.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rental")
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime start_date;

    @NotNull
    private LocalDateTime end_date;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private CarEntity car;

    public RentalEntity() {
    }

    public RentalEntity(Long id, LocalDateTime start_date, LocalDateTime end_date, Double price) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
    }

    public RentalEntity(LocalDateTime start_date, LocalDateTime end_date, Double price) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
    }

    public RentalEntity(LocalDateTime start_date, LocalDateTime end_date, Double price, UserEntity user, CarEntity car) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.user = user;
        this.car = car;
    }

    // Métodos getters y setters para todos los campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
