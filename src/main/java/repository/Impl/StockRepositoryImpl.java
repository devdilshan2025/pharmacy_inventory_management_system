package repository.Impl;

import db.DBConnection;
import javafx.scene.control.Alert;
import repository.StockRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StockRepositoryImpl implements StockRepository {

    @Override
    public void addStockDetails(String itemID, String name, String brand, LocalDate exp, int quantity, double price) throws SQLException {

        Connection  connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO item VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,itemID);
        preparedStatement.setObject(2,name);
        preparedStatement.setObject(3,brand);
        preparedStatement.setObject(4,exp);
        preparedStatement.setObject(5,quantity);
        preparedStatement.setObject(6,price);

        preparedStatement.executeUpdate();

    }

    @Override
    public void deleteItem(String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ? ");
        pstm.setObject(1,itemID);
        pstm.executeUpdate();
    }

    @Override
    public void updateStock(String name, String brand, LocalDate exp, int quantity, double price, String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE item SET Name=?, Brand=?, ExpeDate=?, Quantity=?, UnitPrice=? WHERE ItemCode=?";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setObject(1,name);
        pst.setObject(2,brand);
        pst.setObject(3,exp);
        pst.setObject(4,quantity);
        pst.setObject(5,price);
        pst.setObject(6,itemID);


        int rows = pst.executeUpdate();


        if (rows > 0) {
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully!").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "No item found!").show();
        }
    }

    @Override
    public void updateItemQuantity(String itemCode, int quantity) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE item SET Quantity = Quantity - ?  WHERE ItemCode = ?";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1,quantity);
        pst.setString(2,itemCode);

        pst.executeUpdate();

    }

    @Override
    public ResultSet searchItem(String itemID, String name) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ? OR Name= ?");
        pstm.setObject(1,itemID);
        pstm.setObject(2,name);
        ResultSet resultSet = pstm.executeQuery();

        return resultSet;

    }


    @Override
    public ResultSet getAllStock() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
}
