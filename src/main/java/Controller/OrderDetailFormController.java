package Controller;

import Service.Impl.OrderDetailServiceImpl;
import Service.OrderDetailService;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.OrderDetailShowDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailFormController implements Initializable {

    // Service එක initialize කරගන්න
    OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    @FXML
    private TableView<OrderDetailShowDTO> tblOrderDetails; // Table එක අමතක කරන්න එපා

    @FXML
    private JFXButton btnserch;

    @FXML
    private TableColumn<OrderDetailShowDTO, String> colcusId;

    @FXML
    private TableColumn<OrderDetailShowDTO, String> colcusName;

    @FXML
    private TableColumn<OrderDetailShowDTO, String> coldate;

    @FXML
    private TableColumn<OrderDetailShowDTO, Double> coldiscount;

    @FXML
    private TableColumn<OrderDetailShowDTO, String> colitemId;

    @FXML
    private TableColumn<OrderDetailShowDTO, String> colorderId;

    @FXML
    private TableColumn<OrderDetailShowDTO, Integer> colorderQuantity;

    @FXML
    private TextField txtcusId;

    @FXML
    private TextField txtorderId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table Columns වලට DTO එකේ fields ටික ලස්සනට පවරන්න (Mapping)
        colcusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colcusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colorderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colitemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colorderQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        coldiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    void btnserchOnAction(ActionEvent event) {
        String orderId = txtorderId.getText();
        String cusId = txtcusId.getText();

        // Service එක හරහා දත්ත ටික ලැයිස්තුවක් විදිහට ලබාගන්නවා
        ObservableList<OrderDetailShowDTO> details = orderDetailService.getAllOrderDetails(orderId, cusId);

        if (details != null && !details.isEmpty()) {
            tblOrderDetails.setItems(details);
        } else {
            tblOrderDetails.getItems().clear(); // Table එක clear කරන්න
            new Alert(Alert.AlertType.WARNING, "එම දත්ත සොයාගත නොහැක!").show();
        }
    }
}