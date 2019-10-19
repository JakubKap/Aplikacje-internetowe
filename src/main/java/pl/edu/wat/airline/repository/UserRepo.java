package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {
}
