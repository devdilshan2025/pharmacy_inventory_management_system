package Controller;

import Service.PlaceOrderService;
import Service.Impl.PlaceOrderServiceImpl;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Orders;
import model.dto.StockInfo;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

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
    private TableView<CartItem> tblPlaceOrder;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtcusID;

    ObservableList<CartItem> cartItems = FXCollections.observableArrayList();
    @FXML
    void btnAddToCart(ActionEvent event) {

        cartItems.add(new CartItem(
                txtItemCode.getText(),
                lblItemName.getText(),
                Integer.parseInt(txtQuantity.getText()),
                Double.parseDouble(lblUnitPrice.getText()),
                Double.parseDouble(lblDiscount.getText()),
                calculateTotal(lblUnitPrice.getText(), txtQuantity.getText())

        ));
        tblPlaceOrder.setItems(cartItems);

        clearFields();
        netTotal();

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        placeOrderService.placeaorder(new Orders(
                txtOrderID.getText(),
                LocalDate.now(),
                txtcusID.getText()
        ),cartItems);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        lblDiscount.setText("0.0");
    }

    private  Double calculateTotal(String unitPrice, String quantity){

        Double total = 0.0;
        total = Double.parseDouble(unitPrice) * Integer.parseInt(quantity);
        return total;
    }

    private void clearFields(){

        txtItemCode.setText(null);
        lblItemName.setText(null);
        lblUnitPrice.setText(null);
        lblDiscount.setText("0.0");
        txtQuantity.setText(null);
    }

    private void netTotal(){
        double netTotal = 0.0;
        for (CartItem cartItem: cartItems){
            netTotal+= cartItem.getTotal();

            lblNetTotal.setText(String.valueOf(netTotal));
        }

    }
}
