package net.carRenting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String firstName;
    
    @Size(max = 50)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phoneNumber;
    
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String address;
    
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String city;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String province;
    
    @NotNull
    @NotBlank
    @Size(max = 10)
    private String postalCode;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String country;

    @NotNull
    private java.sql.Date memberSince;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 512)
    private String password;

    @NotNull
    private Boolean role;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "id_rental")
    private RentalEntity rental;

    public CustomerEntity() {
    }

    // Constructor con los campos obligatorios
    public CustomerEntity(String firstName, String lastName, String phoneNumber, String email, String address, String city, String province, String postalCode, String country, java.sql.Date memberSince, String username, String password, Boolean role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.memberSince = memberSince;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor con todos los campos
    public CustomerEntity(Long id, String firstName, String lastName, String phoneNumber, String email, String address, String city, String province, String postalCode, String country, java.sql.Date memberSince, String username, String password, Boolean role, CarEntity car, RentalEntity rental) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.memberSince = memberSince;
        this.username = username;
        this.password = password;
        this.role = role;
        this.car = car;
        this.rental = rental;
    }

    // Métodos getters y setters para todos los campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.sql.Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(java.sql.Date memberSince) {
        this.memberSince = memberSince;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }
}
