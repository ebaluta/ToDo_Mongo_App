package pl.edyta.tododemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String username;

}
