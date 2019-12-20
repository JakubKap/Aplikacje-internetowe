package pl.edu.wat.airline.dtos;

import java.time.LocalDate;

public class UserDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String login;
    private String password;
    private LocalDate birthdate;

    public UserDto() {
    }

    public UserDto(String name, String surname, LocalDate birthdate, String phoneNumber, String email, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
