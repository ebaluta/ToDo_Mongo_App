package pl.edyta.tododemo.ui;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("user-not-found")
public class UserNotFound extends Div {

    public UserNotFound() {
        Label label = new Label("User not found");
        add(label);
    }
}
