package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PharmacyLoginformController {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnCustomers;

    @FXML
    private ImageView image1;

    @FXML
    void btnAdminOnAction(ActionEvent event) {

    }


    Stage stage = new Stage();
    @FXML
    void btnCustomerOnAction(ActionEvent event) {


        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Order_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

}
