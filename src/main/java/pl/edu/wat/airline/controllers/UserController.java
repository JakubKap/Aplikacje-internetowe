package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        return new ResponseEntity<>(usersServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity getByLoginAndPassword(@RequestBody UserCredsDto userCredsDto){
        UserDto userDto = usersServiceImpl.findByLoginAndPassword(userCredsDto);
        if (userDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = usersServiceImpl.addUser(userDto);
        if (savedUserDto == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    @GetMapping("mailToNewUser")
    public void sendMailToNewUser(@RequestParam String userEmail) {
    usersServiceImpl.sendMailToNewUser(userEmail);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(usersServiceImpl.updateUser(userDto), HttpStatus.OK);
    }
}
