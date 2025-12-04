package Controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.StockInfo;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class StockLoginFormController implements Initializable {

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

        String itemId = txtItemId.getText();
        String name = txtname.getText();
        String brand = txtbrand.getText();
        LocalDate expDate = LocalDate.parse(txtExp.getText());
        int quantity = Integer.parseInt(txtQuntity.getText());
        double price = Double.parseDouble(txtPrice.getText());

        try {
            Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pharmacy", "root", "1234");
            String sql = "INSERT INTO item VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,itemId);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,brand);
            preparedStatement.setObject(4,expDate);
            preparedStatement.setObject(5,quantity);
            preparedStatement.setObject(6,price);

            preparedStatement.executeUpdate();
            loadStockDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pharmacy", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ? ");
            pstm.setObject(1,txtItemId.getText());
            pstm.executeUpdate();
            loadStockDetails();
            clearTextField();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pharmacy", "root", "1234");

            String sql = "UPDATE item SET Name=?, Brand=?, ExpeDate=?, Quantity=?, UnitPrice=? WHERE ItemCode=?";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setObject(1,txtname.getText());
            pst.setObject(2,txtbrand.getText());
            pst.setObject(3,LocalDate.parse(txtExp.getText()));
            pst.setObject(4,Integer.parseInt(txtQuntity.getText()));
            pst.setObject(5,Double.parseDouble(txtPrice.getText()));
            pst.setObject(6,txtItemId.getText());


            int rows = pst.executeUpdate();


            if (rows > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully!").show();
                loadStockDetails();
            } else {
                new Alert(Alert.AlertType.WARNING, "No item found!").show();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadStockDetails(){

        stockInfos.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pharmacy", "root", "1234");
            String sql = "SELECT * FROM item";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                StockInfo stockInfo =new StockInfo(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Name"),
                        resultSet.getString("Brand"),
                        resultSet.getDate("ExpeDate").toLocalDate(),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("UnitPrice")
                );

                stockInfos.add(stockInfo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblStock.setItems(stockInfos);


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
