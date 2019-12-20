package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.UserEntity;


public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    @Query(
            "SELECT new pl.edu.wat.airline.entities.UserEntity(u.id, u.name, u.surname, u.birthdate, u.phoneNumber, u.email, u.login, u.password) " +
                    "FROM UserEntity u " +
                    "WHERE u.login = :login AND u.password = :password"
    )
    UserEntity findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    UserEntity findByLogin(String login);
}
