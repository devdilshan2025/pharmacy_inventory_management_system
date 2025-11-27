package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.StockInfo;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class StockLoginFormController implements Initializable {

    ObservableList<StockInfo> stockInfos = FXCollections.observableArrayList();

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

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                        resultSet.getDate("ExpeDate"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("UnitPrice")
                );

                stockInfos.add(stockInfo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblStock.setItems(stockInfos);
    }
}
