package com.example.bookinghotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends AbstractEntity implements Serializable {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @NotBlank
    @NotNull
    @Size(min = 3)
    private String name;
    private String username;
    private String address;
    private String avatar;
    private String passwordToken;//for password recovery
    public String getAddress() {
        return address;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotEmpty
    @Column(unique = true)
    @Email
    private String email;

    //    @JsonIgnore
    @NotEmpty
    @Size(min = 6)
    private String password;

    @NotNull
    @NotEmpty
    private String gender;
//    private LocalDate dateOfBirth;


    //    @Pattern(regexp = "\"^[\\\\+]?[(]?[0-9]{3}[)]?[-\\\\s\\\\.]?[0-9]{3}[-\\\\s\\\\.]?[0-9]{4,6}$\"" )
    @NotNull
    @NotEmpty
    private String phoneNumber;

    private Boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")}
//    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<WebReview> comments;




    public UserInfo(String name, String username, String email, String password, String gender, LocalDate dateOfBirth, String phoneNumber, Boolean active, Set<Role> roles, String token) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
//        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.roles = roles;
        this.token = token;
    }

    @Transient
    private String token;

    public static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }

    public UserInfo() {
    }

    public UserInfo(@NotNull @Size(min = 3) String name, String username, @Email String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<WebReview> getComments() {
        return comments;
    }

    public void setComments(List<WebReview> comments) {
        this.comments = comments;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
