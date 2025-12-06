package Controller.StockController;

import Service.StockService.StockLoginService;
import Service.StockService.StockLoginServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.StockInfo;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class StockLoginFormController implements Initializable {

   // StockLoginController stockLoginController = new StockLoginController();
    StockLoginService stockLoginService = new StockLoginServiceImpl();
    ObservableList<StockInfo> stockInfos = FXCollections.observableArrayList();

    @FXML
    private Button btnClear;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;


    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colExp;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableView<StockInfo> tblStock;

    @FXML
    private TextField txtExp;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuntity;

    @FXML
    private TextField txtbrand;

    @FXML
    private TextField txtname;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        stockLoginService.addStockDetails(txtItemId.getText(), txtname.getText(), txtbrand.getText(), LocalDate.parse(txtExp.getText()), Integer.parseInt(txtQuntity.getText()), Double.parseDouble(txtPrice.getText()));
        loadStockDetails();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        stockLoginService.deleteStockDetails(txtItemId.getText());
        loadStockDetails();
        clearTextField();


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
       clearTextField();

    }

    private void clearTextField(){
        txtItemId.setText(null);
        txtname.setText(null);
        txtbrand.setText(null);
        txtExp.setText(null);
        txtPrice.setText(null);
        txtQuntity.setText(null);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        stockLoginService.updateStock(txtname.getText(), txtbrand.getText(), LocalDate.parse(txtExp.getText()), Integer.parseInt(txtQuntity.getText()), Double.parseDouble(txtPrice.getText()), txtItemId.getText());
        loadStockDetails();


    }

    private void loadStockDetails(){

        stockInfos.clear();
        tblStock.setItems(stockLoginService.getAllStock());


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

       loadStockDetails();

       tblStock.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
           if (newValue !=null){
               txtItemId.setText(newValue.getItemId());
               txtname.setText(newValue.getName());
               txtbrand.setText(newValue.getBrand());
               txtExp.setText(String.valueOf(newValue.getExpDate()));
               txtQuntity.setText(String.valueOf(newValue.getQuantity()));
               txtPrice.setText(String.valueOf(newValue.getPrice()));

           }
       });
    }
}
