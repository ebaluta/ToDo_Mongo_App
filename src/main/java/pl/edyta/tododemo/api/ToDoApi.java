package pl.edyta.tododemo.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edyta.tododemo.entity.Task;
import pl.edyta.tododemo.helper.TaskString;
import pl.edyta.tododemo.repo.TaskRepo;
import pl.edyta.tododemo.repo.UserRepo;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class ToDoApi {

    TaskRepo taskRepo;

    UserRepo userRepo;

    @Autowired
    public ToDoApi(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(value = "/todo/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String showUsersId(@PathVariable Integer userid) {
        String username="";
        try {
            username = userRepo.findById(userid).get().getUsername();
        } catch (NoSuchElementException e){
            e.printStackTrace();
        }
        if(username.equals("")){
            return "User id not found";
        }
        List<Task> userTasks = taskRepo.findAllByUserId(userid);
        userTasks.sort(Task::compareTo);

       return TaskString.getToDoString(userid,username,userTasks);
    }
}
