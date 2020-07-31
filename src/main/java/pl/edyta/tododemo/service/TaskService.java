package pl.edyta.tododemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edyta.tododemo.entity.Task;
import pl.edyta.tododemo.entity.User;
import pl.edyta.tododemo.model.payload.response.user.ToDoResponse;
import pl.edyta.tododemo.repo.TaskRepo;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    private List<ToDoResponse> getTasks(){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity= new HttpEntity(httpHeaders);
        RestTemplate restTemplate= new RestTemplate();

        ToDoResponse[] body = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/todos",
                HttpMethod.GET,
                httpEntity,
                ToDoResponse[].class
        ).getBody();

        List <ToDoResponse> toDoResponseList = Arrays.asList(body);
        return toDoResponseList;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void saveTasks(){
        List <ToDoResponse> toDoResponseList = getTasks();

        toDoResponseList.forEach(item ->{
            Task task = new Task(item.getId(),item.getUserId(),item.getTitle(),item.getCompleted());
            taskRepo.save(task);
        });
    }


}
