package Service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.StockInfo;

import java.time.LocalDate;

public interface StockLoginService {

    void addStockDetails(String itemID, String name, String brand, LocalDate exp, int quantity, double price);

    ObservableList<StockInfo> getAllStock();

    void deleteStockDetails(String itemID);

    void updateStock( String name, String brand, LocalDate exp, int quantity, double price, String itemID);

    StockInfo searchItem(String text, String txtnameText);

    boolean updateItemQuantity(ObservableList<CartItem> cartItems);
}
