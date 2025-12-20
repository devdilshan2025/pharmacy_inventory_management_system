package Service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Orders;

public interface OrderDetailService {

    boolean addOrderDetail(Orders orders, ObservableList<CartItem> cartItems);


}
