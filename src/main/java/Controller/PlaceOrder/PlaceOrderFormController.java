package Controller.PlaceOrder;

import Service.PlaceOrderService;
import Service.Impl.PlaceOrderServiceImpl;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dto.Customer;
import model.dto.StockInfo;

public class PlaceOrderFormController {

    PlaceOrderService placeOrderService = new PlaceOrderServiceImpl();

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colTotal1;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDiscount;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<?> tblPlaceOrder;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtcusID;

    @FXML
    void btnAddToCart(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {

        StockInfo item = placeOrderService.searchItem(txtItemCode.getText(), null);
        lblItemName.setText(item.getName());
        lblUnitPrice.setText(String.valueOf(item.getPrice()));

    }

    @FXML
    void txtcusIDOnAction(ActionEvent event) {

        Customer customer = placeOrderService.getCustomer(txtcusID.getText());
        lblCusName.setText(customer.getCustomerName());

    }

}
