package repository.Impl;

import db.DBConnection;
import javafx.scene.control.Alert;
import repository.SupplyRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplyRepositoryImpl implements SupplyRepository {


    @Override
    public ResultSet getAllSuppliers() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    @Override
    public void addSuppliers(String supplierID, String name, String contactPerson, String phone, String email, String address, String notes) throws SQLException {
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
    }

    @Override
    public void deleteStockDetails(String supplierID) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM supplier WHERE supplier_id = ? ");
        pstm.setObject(1,supplierID);
        pstm.executeUpdate();
    }

    @Override
    public void updateSupplier(String name, String cntactPerson, String phone, String email, String address, String notes, String supplierID) throws SQLException {

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

    }
}
