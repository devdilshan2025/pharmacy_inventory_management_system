package Service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Orders;

public interface OrderDetailService {

    void addOrderDetail(Orders orders, ObservableList<CartItem> cartItems);


}
