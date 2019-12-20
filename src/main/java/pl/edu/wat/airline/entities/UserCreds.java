package pl.edu.wat.airline.entities;

import lombok.Data;

@Data
public class UserCreds {
    private String login;
    private String password;

    public UserCreds() {
    }

    public UserCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
