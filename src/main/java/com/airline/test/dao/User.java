package com.airline.test.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")

public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String name;
    private String surname;
    private String birthdate;
    private String phoneNumber;
    private String email;
    private String login;
    private String password;
    private String salt;
}
