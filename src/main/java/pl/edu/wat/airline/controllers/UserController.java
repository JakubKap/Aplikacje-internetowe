package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.UserCredsDto;
import pl.edu.wat.airline.dtos.UserDto;
import pl.edu.wat.airline.services.UsersServiceImpl;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private UsersServiceImpl usersServiceImpl;

    @Autowired
    public UserController(UsersServiceImpl usersServiceImpl)
    {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("all_users")
    public Iterable<UserDto> getAllUsers(){
        return usersServiceImpl.findAll();
    }

    @PostMapping("login")
    public UserDto getByLoginAndPassword(@RequestBody UserCredsDto userCredsDto){
        return usersServiceImpl.findByLoginAndPassword(userCredsDto);
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return usersServiceImpl.addUser(userDto);
    }

    @GetMapping("mailToNewUser")
    public void sendMailToNewUser(@RequestParam String userEmail) {
    usersServiceImpl.sendMailToNewUser(userEmail);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return usersServiceImpl.updateUser(userDto);
    }
}
