package Controller.SupplierController;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.SupplyInfo;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SupplierLoginFormController implements Initializable {

    SupplierLoginService supplierLoginService = new SupplierLoginController();
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

        supplierLoginService.addSuppliers(txtSupplierID.getText(), txtName.getText(), txtContactPerson.getText(), txtPhone.getText(), txtEmail.getText(), txtAddress.getText(), txtNotes.getText());
        loadSupplyDetails();

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

        supplierLoginService.deleteStockDetails(txtSupplierID.getText());
        loadSupplyDetails();
        clearTextField();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        supplierLoginService.updateSupplier(txtName.getText(), txtContactPerson.getText(), txtPhone.getText(), txtEmail.getText(), txtAddress.getText(), txtNotes.getText(), txtSupplierID.getText());
        loadSupplyDetails();
    }

    private void loadSupplyDetails(){

        supplyInfos.clear();

        tblSupplier.setItems(supplierLoginService.getAllSuppliers());

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

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue !=null){
                txtSupplierID.setText(newValue.getSupplierID());
                txtName.setText(newValue.getName());
                txtContactPerson.setText(newValue.getContactPerson());
                txtPhone.setText(String.valueOf(newValue.getPhone()));
                txtEmail.setText(String.valueOf(newValue.getEmail()));
                txtAddress.setText(String.valueOf(newValue.getAddress()));
                txtNotes.setText(String.valueOf(newValue.getNotes()));

            }
        });
    }
}
