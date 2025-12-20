package Service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Orders;
import model.dto.StockInfo;

import java.sql.SQLException;

public interface PlaceOrderService {

     StockInfo searchItem(String itemCode, Object o);

    Customer getCustomer(String cusID);

    void placeaorder(Orders orders, ObservableList<CartItem> cartItems) throws SQLException;
}
