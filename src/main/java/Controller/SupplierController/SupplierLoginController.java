package Controller.SupplierController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.StockInfo;
import model.dto.SupplyInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierLoginController implements SupplierLoginService {

    @Override
    public void addSuppliers(String supplierID, String name, String contactPerson, String phone, String email, String address, String notes){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO supplier VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,supplierID);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,contactPerson);
            preparedStatement.setObject(4,phone);
            preparedStatement.setObject(5,email);
            preparedStatement.setObject(6,address);
            preparedStatement.setObject(7,notes);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SupplyInfo> getAllSuppliers(){

        ObservableList<SupplyInfo> supplyInfos = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
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

        return supplyInfos;

    }

    @Override
    public void deleteStockDetails(String supplierID){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM supplier WHERE supplier_id = ? ");
            pstm.setObject(1,supplierID);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void updateSupplier(String name, String cntactPerson, String phone, String email, String address, String notes, String supplierID){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE supplier SET name=?, contact_Person=?, phone=?, email=?, Address=?, notes=? WHERE supplier_id=?";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setObject(1,name);
            pst.setObject(2,cntactPerson);
            pst.setObject(3,phone);
            pst.setObject(4,email);
            pst.setObject(5,address);
            pst.setObject(6,notes);
            pst.setObject(7,supplierID);


            int rows = pst.executeUpdate();


            if (rows > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully!").show();

            } else {
                new Alert(Alert.AlertType.WARNING, "No item found!").show();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
