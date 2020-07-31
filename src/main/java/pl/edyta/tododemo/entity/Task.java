package pl.edyta.tododemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
public class Task implements Comparable <Task> {

    @Id
    private Integer id;

    private Integer userId;
    private String title;
    private Boolean completed;


    @Override
    public int compareTo(Task o) {
        return o.getCompleted() ? 1 : -1 ;
    }
}
