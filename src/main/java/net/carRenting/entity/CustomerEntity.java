package net.carRenting.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String firstName;

    @Size(min = 3, max = 255)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 9)
    @Pattern(regexp = "^[0-9]+$", message = "Number phone only have numbers.")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Email
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
    @NotBlank
    @Size(min = 6, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    @Size(min = 64, max = 64)
    @Pattern(regexp = "^[a-fA-F0-9]+$", message = "Password must be hexadecimal")
    private String password = "05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e";

    @NotNull
    private Boolean role = false;

    @OneToMany(mappedBy = "customer", fetch = jakarta.persistence.FetchType.LAZY)
    private List<RentalEntity> rentals;

    @OneToMany(mappedBy = "customer", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CarEntity> cars;

    public CustomerEntity() {
        rentals = new ArrayList<>();
        cars = new ArrayList<>();
    }

    // Constructor con todos los campos
    public CustomerEntity(Long id, String firstName, String lastName, String phoneNumber, String email, String address,
            String city, String province, String postalCode, String country, String username,
            String password, Boolean role) {
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
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor con los campos obligatorios
    public CustomerEntity(String firstName, String lastName, String phoneNumber, String email, String address,
            String city, String province, String postalCode, String country, String username,
            String password, Boolean role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public CustomerEntity(String username, String password) {
        this.username = username;
        this.password = password;
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

    public int getRentals() {
        return rentals.size();
    }

    public int getCars() {
        return cars.size();
    }
}
