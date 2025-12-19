package Service.Impl;

import Service.StockLoginService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.CartItem;
import model.dto.StockInfo;
import repository.StockRepository;
import repository.Impl.StockRepositoryImpl;

import java.sql.*;
import java.time.LocalDate;

public class StockLoginServiceImpl implements StockLoginService {

    StockRepository stockRepository = new StockRepositoryImpl();

    @Override
    public void addStockDetails(String itemID, String name, String brand, LocalDate exp, int quantity, double price){

        try {


            stockRepository.addStockDetails(itemID, name, brand, exp, quantity, price);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<StockInfo> getAllStock(){

        ObservableList<StockInfo> stockInfos = FXCollections.observableArrayList();


        try {

            ResultSet  resultSet = stockRepository.getAllStock();

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



          stockRepository.deleteItem(itemID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void updateStock( String name, String brand, LocalDate exp, int quantity, double price, String itemID){

        try {


          stockRepository.updateStock(name, brand, exp, quantity, price, itemID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StockInfo searchItem(String itemID, String name) {

        try {

            ResultSet resultSet = stockRepository.searchItem(itemID, name);
            resultSet.next();
            return new StockInfo(
                    resultSet.getString("ItemCode"),
                    resultSet.getString("Name"),
                    resultSet.getString("Brand"),
                    resultSet.getDate("ExpeDate").toLocalDate(),
                    resultSet.getInt("Quantity"),
                    resultSet.getDouble("UnitPrice")

            );
        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "This ItemCode not in DataBase");
            alert.show();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateItemQuantity(ObservableList<CartItem> cartItems) {

        for (CartItem cartItem: cartItems)
            try {
                stockRepository.updateItemQuantity(cartItem.getItemCode(), cartItem.getQuantity());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }


}
