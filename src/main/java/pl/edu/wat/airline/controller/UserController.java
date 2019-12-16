package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.User;
import pl.edu.wat.airline.entity.UserCreds;
import pl.edu.wat.airline.service.EmailService;
import pl.edu.wat.airline.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService users;
    private EmailService email;

    @Autowired
    public UserController(UserService users, EmailService email)
    {
        this.users = users;
        this.email = email;
    }


    @GetMapping("all_users")
    public Iterable<User> getAllUsers(){
        return users.findAll();
    }

    @GetMapping
    public Optional<User> getById(@RequestParam Long id) {
        return users.findById(id);
    }

    @PostMapping("login")
    public Optional<User> getByLoginAndPassword(@RequestBody UserCreds userCreds){
        return users.findByLoginAndPassword(userCreds);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        try {
            email.sendEmail(user.getEmail(),"AirportApp new member", "Faithfully AirportApp team");
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
        return users.save(user, null);
    }

    @PutMapping
    public User updateUser(@RequestParam Long id, @RequestBody User userRequest) {
        return users.findById(id).map(user ->{
            user.setName(userRequest.getName());
            user.setSurname(userRequest.getSurname());
            user.setBirthdate(userRequest.getBirthdate());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setEmail(userRequest.getEmail());
            user.setLogin(userRequest.getLogin());
            user.setPassword(userRequest.getPassword());
            return users.save(user, id);
        }).orElseThrow(() -> new RuntimeException("UserId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return users.findById(id).map(user -> {
            users.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("UserId " + id + " not found."));
    }
}
