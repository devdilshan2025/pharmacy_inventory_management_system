package Controller.StockController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.StockInfo;

import java.sql.*;
import java.time.LocalDate;

public class StockLoginController implements StockLoginService {

    @Override
    public void addStockDetails(String itemID, String name, String brand, LocalDate exp, int quantity, double price){

        try {
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<StockInfo> getAllStock(){

        ObservableList<StockInfo> stockInfos = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
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
        return stockInfos;

    }

    @Override
    public void deleteStockDetails(String itemID){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ? ");
            pstm.setObject(1,itemID);
            pstm.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void updateStock( String name, String brand, LocalDate exp, int quantity, double price, String itemID){

        try {
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
