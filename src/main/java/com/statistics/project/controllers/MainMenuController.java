import com.statistics.project.database.DATA_BASE;
import com.statistics.project.util.FXUTIL;
import com.statistics.project.util.STRING_VALIDATOR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {
    @FXML
    private Hyperlink createAccountHL;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button loginBtn;

    @FXML
    private HBox logoHeader;

    @FXML
    private AnchorPane pane;

    @FXML
    private PasswordField passwordPField;

    @FXML
    private Hyperlink resetAccountHL;

    @FXML
    private Label warningLbl;

    @FXML
    void onLoginClick(ActionEvent event) {
        System.out.println("Log in button is clicked!");
        String emailInput = emailTextField.getText();
        String pwInput = passwordPField.getText();

        if(STRING_VALIDATOR.email_Validator(emailInput)) {
            if(DATA_BASE.authenticateLogIn(emailInput, pwInput)) {
                changeWarninglbl("Success!", "#228B22");
                showAlert("Log in Success!", Alert.AlertType.INFORMATION);
                //proceed to login!



            }else{
                System.out.println("Incorrect user/pass");
                showAlert("incorrect user/pass", Alert.AlertType.WARNING);
            }
        }else{
            changeWarninglbl("Invalid Email!", "#FF0000");

            emailTextField.setText("");
            passwordPField.setText("");

            System.out.println("Please enter a valid email");
        }

    }

    @FXML
    void onTextFieldClick(){
        changeWarninglbl("", "#000000");

    }

    public void changeWarninglbl(String text, String color) {
        warningLbl.setText(text);
        warningLbl.setTextFill(Color.web(color));
    }

    public void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message);
        alert.showAndWait();

    }

    @FXML
    void changePasswordOnClick(MouseEvent event) {

    }

    @FXML
    void createAccountOnClick(MouseEvent event) throws IOException {
        System.out.println("Button clicked!");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Registration-Form.fxml"));
        Parent menuParent = loader.load();
        Scene scene = new Scene(menuParent);
        RegistrationFormController controller = loader.getController();
        controller.initData();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
       window.showAndWait();

    }

}