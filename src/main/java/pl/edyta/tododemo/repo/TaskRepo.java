package pl.edyta.tododemo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edyta.tododemo.entity.Task;

import java.util.List;


@Repository
public interface TaskRepo extends MongoRepository <Task, Integer> {
    List<Task> findAllByUserId(Integer id);
    Task findDistinctById(Integer id);

}
