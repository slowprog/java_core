package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {
    @FXML
    public TextArea jta;
    @FXML
    public TextField jtf;

    public void sendMsgOnAction(ActionEvent actionEvent) {
        String str = jtf.getText();
        jtf.clear();
        jta.appendText(str + "\n");
    }
}
