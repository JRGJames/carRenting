package net.carRenting.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    private Date pickupDate;

    @NotNull
    private Date dropoffDate;

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

   @OneToMany(mappedBy = "rental", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CarEntity> cars;

    public RentalEntity() {
        cars = new ArrayList<>();
    }

    public RentalEntity(Long id, Date pickupDate, Date dropoffDate, String pickupLocation, String dropoffLocation, Float cost) {
        this.id = id;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.cost = cost;
    }

    public RentalEntity(Date pickupDate, Date dropoffDate, String pickupLocation, String dropoffLocation, Float cost) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.cost = cost;
    }

    public RentalEntity(Date pickupDate, Date dropoffDate, String pickupLocation, String dropoffLocation, Float cost, CustomerEntity customer) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.cost = cost;
        this.customer = customer;
    }

    // Métodos getters y setters para todos los campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(Date dropoffDate) {
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

    public int getCars() {
        return cars.size();
    }
}
