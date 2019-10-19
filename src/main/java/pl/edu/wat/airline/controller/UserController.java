package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.User;
import pl.edu.wat.airline.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService users;

    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }

    @GetMapping("all_users")
    public Iterable<User> getAllUsers(){
        return users.findAll();
    }

    @GetMapping
    public Optional<User> getById(@RequestParam Long id) {
        return users.findById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return users.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return users.save(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) {
        users.deleteById(id);
    }
}
