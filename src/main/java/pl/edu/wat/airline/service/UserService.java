package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.User;
import pl.edu.wat.airline.repository.UserRepo;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new User("Szymon","Bocian", LocalDate.of(1994,3,10),"+48 601 699 730","szymon.bocian@student.wat.edu.pl","SzymonBocian","Szymon","1234"));
        save(new User("Jakub","Kapusta", LocalDate.of(1996,1,1),"+48 601 601 601","jakub.kapusta@student.wat.edu.pl","JakubKapusta","Jakub","4321"));
    }
}
