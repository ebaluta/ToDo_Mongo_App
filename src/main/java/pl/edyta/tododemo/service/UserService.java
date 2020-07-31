package pl.edyta.tododemo.service;


import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edyta.tododemo.entity.User;
import pl.edyta.tododemo.model.payload.response.user.UserResponse;
import pl.edyta.tododemo.repo.UserRepo;


import java.util.Arrays;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepo userRepo;

    private List<UserResponse> getUsers(){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity= new HttpEntity(httpHeaders);
        RestTemplate restTemplate= new RestTemplate();

        UserResponse [] userResponses = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users",
                HttpMethod.GET,
                httpEntity,
                UserResponse[].class
        ).getBody();

        List <UserResponse> userResponseList = Arrays.asList(userResponses);
        return userResponseList;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveUsers(){
        List <UserResponse> userResponseList = getUsers();

        userResponseList.forEach(item -> {
            User user = new User(item.getId(),item.getName(),item.getUsername());
            userRepo.save(user);
        });


    }


}
