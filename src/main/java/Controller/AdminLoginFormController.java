package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.application.Platform;

import java.io.IOException;

public class AdminLoginFormController {

    Stage  stage = new Stage();

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        String name = txtUserName.getText();
        String password = txtPassword.getText();

        if (name.equals("suwani") && password.equals("1234")) {


            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Stock&SupplyLogin_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login Failed");
            alert.setContentText("Invalied Username or Password");
            alert.show();
        }
    }

}
