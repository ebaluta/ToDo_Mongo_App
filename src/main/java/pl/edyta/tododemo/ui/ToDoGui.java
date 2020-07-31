package pl.edyta.tododemo.ui;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edyta.tododemo.entity.Task;
import pl.edyta.tododemo.helper.TaskString;
import pl.edyta.tododemo.repo.TaskRepo;
import pl.edyta.tododemo.repo.UserRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Route("tasks")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css")
public class ToDoGui extends VerticalLayout {

    TaskRepo taskRepo;

    UserRepo userRepo;

    Div userTodoContent;

    Label labelInfo;

    @Autowired
    public ToDoGui(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;

        userTodoContent = new Div();
        labelInfo = new Label();
        IntegerField integerField = new IntegerField("User id", "Insert user id: ");
        add(integerField);

        Button button = new Button("Show users tasks");

        Label label = new Label("Click on task to mark it as completed/uncompleted");

        button.addClickListener(e -> {
            if (!integerField.isEmpty()) {
                Integer userid = integerField.getValue();
                remove(label);
                add(label);
                showTaskInTable(userid);
            }
        });
        button.addClickShortcut(Key.ENTER);

        add(button);
        add(userTodoContent);
        add(labelInfo);


    }

    private void showTasksOfUser(Integer userid) {

        userTodoContent.removeAll();
        String username = "";
        try {
            username = userRepo.findById(userid).get().getUsername();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        if (username.equals("")) {
            labelInfo.setText("User Not found");
            add(labelInfo);
        }
        List<Task> userTasks = taskRepo.findAllByUserId(userid);
        userTasks.sort(Task::compareTo);
        String taskStr = TaskString.getToDoString(userid,username,userTasks);

        Label label = new Label(taskStr);
        label.getStyle().set("white-space", "pre-wrap");
        userTodoContent.add(label);
        add(userTodoContent);

    }

    private void showTaskInTable(Integer userid) {
        userTodoContent.removeAll();
        labelInfo.removeAll();

        String username = "";
        try {
            username = userRepo.findById(userid).get().getUsername();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            labelInfo.setText("User Not found");
        }
        List<Task> userTasks = taskRepo.findAllByUserId(userid);
        userTasks.sort(Task::compareTo);
        Label label = new Label(username + " tasks: ");
        userTodoContent.add(label);


        Grid<Task> tasksGrid = new Grid<>();
        tasksGrid.setClassName("table");

        tasksGrid.setItems(userTasks);
        tasksGrid.addColumn(Task::getId).setHeader("Task id");
        tasksGrid.addColumn(Task::getTitle).setHeader("Title");
        tasksGrid.addColumn(Task::getCompleted).setHeader("Completed");

        tasksGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        SingleSelect<Grid<Task>, Task> taskSelected = tasksGrid.asSingleSelect();


        tasksGrid.addItemClickListener(e -> {
            Task selectedTask = e.getItem();
            selectedTask.setCompleted(!selectedTask.getCompleted());
            taskRepo.save(selectedTask);
            labelInfo.setText("The status of task: " + selectedTask.getTitle() + " of user: " + selectedTask.getUserId() + " is change from: " + !selectedTask.getCompleted() + " to : "
                    + selectedTask.getCompleted());
        });

        userTodoContent.add(tasksGrid);
        userTodoContent.setWidthFull();
        add(userTodoContent);
        add(labelInfo);
    }
}
