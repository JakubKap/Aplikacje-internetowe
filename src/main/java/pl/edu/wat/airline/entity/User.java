package pl.edu.wat.airline.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Table(name = "ai_user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String login;
    private String password;
    private String salt;
    private LocalDate birthdate;

    public User() {
    }

    public User(String name, String surname, LocalDate birthdate, String phoneNumber, String email, String login, String password, String salt) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }
}
