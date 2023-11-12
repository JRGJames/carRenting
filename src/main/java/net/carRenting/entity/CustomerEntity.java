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
    private String first_name;
    
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String last_name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phone_number;
    
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
    private String postal_code;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String country;

    @NotNull
    private java.sql.Date member_since;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 512)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "id_rental")
    private RentalEntity rental;

    public CustomerEntity() {
    }

    // Constructor con los campos obligatorios
    public CustomerEntity(String first_name, String last_name, String phone_number, String email, String address, String city, String province, String postal_code, String country, java.sql.Date member_since, String username, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
        this.country = country;
        this.member_since = member_since;
        this.username = username;
        this.password = password;
    }

    // Constructor con todos los campos
    public CustomerEntity(Long id, String first_name, String last_name, String phone_number, String email, String address, String city, String province, String postal_code, String country, java.sql.Date member_since, String username, String password, CarEntity car, RentalEntity rental) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
        this.country = country;
        this.member_since = member_since;
        this.username = username;
        this.password = password;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.sql.Date getMember_since() {
        return member_since;
    }

    public void setMember_since(java.sql.Date member_since) {
        this.member_since = member_since;
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
