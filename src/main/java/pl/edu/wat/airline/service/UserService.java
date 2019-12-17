package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.User;
import pl.edu.wat.airline.entity.UserCreds;
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

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findByLoginAndPassword(UserCreds userCreds){
        return userRepo.findByLoginAndPassword(userCreds.getLogin(), userCreds.getPassword());
    }

    public User save(User user, Long id) {
        User foundUser = userRepo.findByLogin(user.getLogin());

        if(foundUser == null || foundUser.getId().equals(id)
        || (foundUser != null && foundUser.getId().equals(id))) {
            return userRepo.save(user);
        }
        else {
            return null;
        }
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//        save(new User("Szymon","Bocian", LocalDate.of(1994,3,10),"+48 601 699 730","szymon.bocian@student.wat.edu.pl","SzymonBocian2","Szymon"), null);
//        save(new User("Jakub","Kapusta", LocalDate.of(1996,1,1),"+48 601 601 601","jakub.kapusta@student.wat.edu.pl","JakubKapusta2","Jakub"),null);
//        save(new User("Jakub","Kapusta", LocalDate.of(1996,1,1),"+48 601 601 601","jakub.kapusta@student.wat.edu.pl","jakubk2","jk"), null);
//    }
}
