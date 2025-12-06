package Service.StockService;

import javafx.collections.ObservableList;
import model.dto.StockInfo;

import java.time.LocalDate;

public interface StockLoginService {

    void addStockDetails(String itemID, String name, String brand, LocalDate exp, int quantity, double price);

    ObservableList<StockInfo> getAllStock();

    void deleteStockDetails(String itemID);

    void updateStock( String name, String brand, LocalDate exp, int quantity, double price, String itemID);

}
