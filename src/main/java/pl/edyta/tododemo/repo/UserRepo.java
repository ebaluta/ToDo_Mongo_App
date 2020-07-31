package pl.edyta.tododemo.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edyta.tododemo.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, Integer> {
        Optional<User> findById(Integer id);
}
