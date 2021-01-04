package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChNickController {

    @FXML
    private TextField nicknameField;
    @FXML
    private TextArea chNickTextArea;
    @FXML
    private Button chButton;

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void tryToChNick(ActionEvent actionEvent) {

        String nickname = nicknameField.getText().trim();

        controller.tryToChNick(nickname);

        Stage stage = (Stage) chButton.getScene().getWindow();
        stage.close();
    }

    public void addMessage(String msg) {
        chNickTextArea.appendText(msg + "\n");
    }

}
