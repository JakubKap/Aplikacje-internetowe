package pl.edu.wat.airline.services;


import pl.edu.wat.airline.dtos.UserCredsDto;
import pl.edu.wat.airline.dtos.UserDto;


public interface UsersService {
    Iterable<UserDto> findAll();

    UserDto findByLoginAndPassword(UserCredsDto userCredsDto);

    UserDto addUser(UserDto userDto);

    void sendMailToNewUser(String userEmail);

    UserDto updateUser(UserDto userDto);
}
