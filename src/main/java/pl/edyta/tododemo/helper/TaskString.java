package pl.edyta.tododemo.helper;

import org.springframework.stereotype.Component;
import pl.edyta.tododemo.entity.Task;

import java.util.List;

@Component
public class TaskString {

    public TaskString() {
    }

    public static String getToDoString(Integer userid, String username, List<Task> taskList){
        StringBuilder stringBuilder = new StringBuilder("User #" + userid + " (" + username + ")" + "\n");

        for (Task task : taskList) {
            stringBuilder.append(String.format("\t[%s] task: %s",
                    task.getCompleted() ? "*" : " ",
                    task.getTitle()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
