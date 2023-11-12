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
@Table(name = "rental")
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idCustomer;

    @NotNull
    private Long idCar;

    @NotNull
    private java.sql.Date pickupDate;

    @NotNull
    private java.sql.Date dropoffDate;

    @NotNull
    @Size(max = 50)
    private String pickupLocation;

    @NotNull
    @Size(max = 50)
    private String dropoffLocation;

    @NotNull
    private Float cost;

    @ManyToOne
    @JoinColumn(name = "id_customer", insertable = false, updatable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "id_car", insertable = false, updatable = false)
    private CarEntity car;

    public RentalEntity() {
    }

    // Constructor con todos los campos
    public RentalEntity(Long id, Long idCustomer, Long idCar, java.sql.Date pickupDate, java.sql.Date dropoffDate, String pickupLocation, String dropoffLocation, Float cost, CustomerEntity customer, CarEntity car) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idCar = idCar;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.cost = cost;
        this.customer = customer;
        this.car = car;
    }

    // Métodos getters y setters para todos los campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public java.sql.Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(java.sql.Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public java.sql.Date getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(java.sql.Date dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
