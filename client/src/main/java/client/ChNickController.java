package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChNickController {

    @FXML
    private TextField nicknameField;
    @FXML
    private TextArea chNickTextArea;

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void tryToChNick(ActionEvent actionEvent) {

        String nickname = nicknameField.getText().trim();

        controller.tryToChNick(nickname);
    }

    public void addMessage(String msg) {
        chNickTextArea.appendText(msg + "\n");
    }

}
