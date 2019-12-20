package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    @Query(
            "SELECT new pl.edu.wat.airline.entities.User(u.id, u.name, u.surname, u.birthdate, u.phoneNumber, u.email, u.login, u.password) " +
                    "FROM User u " +
                    "WHERE u.login = :login AND u.password = :password"
    )
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    User findByLogin(String login);
}
