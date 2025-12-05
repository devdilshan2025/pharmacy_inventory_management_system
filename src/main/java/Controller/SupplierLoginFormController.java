package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.StockInfo;
import model.dto.SupplyInfo;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SupplierLoginFormController implements Initializable {

    ObservableList<SupplyInfo> supplyInfos = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactPerson;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNotes;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TableView<SupplyInfo> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactPerson;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNotes;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSupplierID;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        try {
            Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pharmacy", "root", "1234");
            String sql = "INSERT INTO supplier VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,txtSupplierID.getText());
            preparedStatement.setObject(2,txtName.getText());
            preparedStatement.setObject(3,txtContactPerson.getText());
            preparedStatement.setObject(4,txtPhone.getText());
            preparedStatement.setObject(5,txtEmail.getText());
            preparedStatement.setObject(6,txtAddress.getText());
            preparedStatement.setObject(7,txtNotes.getText());

            preparedStatement.executeUpdate();
            loadSupplyDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearTextField();

    }

    private void clearTextField(){
        txtSupplierID.setText(null);
        txtName.setText(null);
        txtContactPerson.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txtAddress.setText(null);
        txtNotes.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadSupplyDetails(){

        clearTextField();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pharmacy", "root", "1234");
            String sql = "SELECT * FROM supplier";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                SupplyInfo supplyInfo =new SupplyInfo(
                        resultSet.getString("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact_Person"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("Address"),
                        resultSet.getString("notes")
                );

                supplyInfos.add(supplyInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblSupplier.setItems(supplyInfos);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        loadSupplyDetails();
    }
}
