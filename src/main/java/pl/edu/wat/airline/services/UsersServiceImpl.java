package pl.edu.wat.airline.services;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.dtos.UserDto;
import pl.edu.wat.airline.entities.UserEntity;
import pl.edu.wat.airline.dtos.UserCredsDto;
import pl.edu.wat.airline.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    private UsersRepository usersRepository;
    private EmailService email;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, EmailService email) {
        this.usersRepository = usersRepository;
        this.email = email;
    }

    public Iterable<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        usersRepository.findAll().forEach(u -> userDtos.add(new UserDto(u.getName(), u.getSurname(), u.getBirthdate(),
                u.getPhoneNumber(), u.getEmail(), u.getLogin(), u.getPassword())));

        return userDtos;
    }

    public UserDto findByLoginAndPassword(UserCredsDto userCredsDto){
        UserEntity userEntity = usersRepository.findByLoginAndPassword(userCredsDto.getLogin(), userCredsDto.getPassword());

        if(userEntity == null){
            return null;
        }

        return new UserDto(userEntity.getName(), userEntity.getSurname(), userEntity.getBirthdate(),
                userEntity.getPhoneNumber(), userEntity.getEmail(), userEntity.getLogin(), userEntity.getPassword());
    }

    public UserDto addUser(UserDto userDto) {
        UserEntity foundUserEntity = usersRepository.findByLogin(userDto.getLogin());

        if(foundUserEntity != null) {
            return null;
        }

        UserEntity userEntity = new UserEntity(userDto.getName(), userDto.getSurname(), userDto.getBirthdate(), userDto.getPhoneNumber(),
               userDto.getEmail(), userDto.getLogin(), userDto.getPassword());

        UserEntity savedUserEntity = usersRepository.save(userEntity);

        return new UserDto(savedUserEntity.getName(), savedUserEntity.getSurname(), savedUserEntity.getBirthdate(), savedUserEntity.getPhoneNumber(),
                savedUserEntity.getEmail(), savedUserEntity.getLogin(), savedUserEntity.getPassword());
    }

    public void sendMailToNewUser(String userEmail){
        try {
            email.sendEmail(userEmail,"AirportApp new member", "Your registration finished with succcess. Please, log in to AirportApp using your account.");
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
    }

    public UserDto updateUser(UserDto userDto){
        Long userEntityId = usersRepository.findByLogin(userDto.getLogin()).getId();

        UserEntity updatedUserEntity =  usersRepository.findById(userEntityId).map(userEntity ->{
            userEntity.setName(userDto.getName());
            userEntity.setSurname(userDto.getSurname());
            userEntity.setBirthdate(userDto.getBirthdate());
            userEntity.setPhoneNumber(userDto.getPhoneNumber());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setLogin(userDto.getLogin());
            userEntity.setPassword(userDto.getPassword());
            return usersRepository.save(userEntity);
        }).orElseThrow(() -> new RuntimeException("UserId " + userEntityId + " not found."));

        return new UserDto(updatedUserEntity.getName(), updatedUserEntity.getSurname(), updatedUserEntity.getBirthdate(), updatedUserEntity.getPhoneNumber(),
                updatedUserEntity.getEmail(), updatedUserEntity.getLogin(), updatedUserEntity.getPassword());
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//        save(new User("Szymon","Bocian", LocalDate.of(1994,3,10),"+48 601 699 730","szymon.bocian@student.wat.edu.pl","SzymonBocian2","Szymon"), null);
//        save(new User("Jakub","Kapusta", LocalDate.of(1996,1,1),"+48 601 601 601","jakub.kapusta@student.wat.edu.pl","JakubKapusta2","Jakub"),null);
//        save(new User("Jakub","Kapusta", LocalDate.of(1996,1,1),"+48 601 601 601","jakub.kapusta@student.wat.edu.pl","jakubk2","jk"), null);
//    }
}
