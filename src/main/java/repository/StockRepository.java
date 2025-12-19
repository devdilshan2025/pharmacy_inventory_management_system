package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface StockRepository {

    ResultSet searchItem(String itemID, String name) throws SQLException;

    ResultSet getAllStock() throws SQLException;

    void addStockDetails(String itemID, String name, String brand, LocalDate exp, int quantity, double price) throws SQLException;

    void deleteItem(String itemID) throws SQLException;


    void updateStock(String name, String brand, LocalDate exp, int quantity, double price, String itemID) throws SQLException;

    void updateItemQuantity(String itemCode, int quantity) throws SQLException;
}
