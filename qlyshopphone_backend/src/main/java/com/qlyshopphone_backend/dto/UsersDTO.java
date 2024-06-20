package com.qlyshopphone_backend.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {
    private int userId;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String phoneNumber;
    private String idCard;
    private int genderId;
    private String facebook;
    private String email;
    private String address;
    private String fullName;
    private Date birthday;
    private int active;
    private int employee;
    private int roleId;
}
