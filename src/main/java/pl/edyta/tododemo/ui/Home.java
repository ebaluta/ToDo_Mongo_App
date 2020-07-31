package pl.edyta.tododemo.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("")
public class Home extends Div {

    public Home() {
        Label label = new Label();
        label.setText(text());
        label.getStyle().set("white-space", "pre-wrap");
        add(label);
    }

    private String text (){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("Available endpoint: \n");
        stringBuilder.append("\t - /tasks - to see/update all users tasks \n");
        stringBuilder.append("\t - /todo/{userid} - to see one user task");
        return stringBuilder.toString();
    }
}
