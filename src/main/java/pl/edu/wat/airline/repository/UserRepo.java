package pl.edu.wat.airline.repository;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.wat.airline.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    @Query(
            "SELECT new pl.edu.wat.airline.entity.User(u.id, u.name, u.surname, u.birthdate, u.phoneNumber, u.email, u.login, u.password) " +
                    "FROM User u " +
                    "WHERE u.login = :login AND u.password = :password"
    )
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    User findByLogin(String login);
}
