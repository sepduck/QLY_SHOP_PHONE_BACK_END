package com.qlyshopphone_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "start_day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDay;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "gender_id")
    private int genderId;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "active")
    private int active;

    @Column(name = "employee")
    private int employee;

    @Column(name = "birthday")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Roles> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cart = new ArrayList<>();
}
