package Service.Impl;

import Service.OrderDetailService;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.OrderDetail;
import model.dto.Orders;
import repository.Impl.OrderDetailRepositoryImpl;
import repository.OrderDetailRepository;

import java.sql.SQLException;

public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDetailRepository orderDetailRepository = new OrderDetailRepositoryImpl();

    public void addOrderDetail(Orders orders, ObservableList<CartItem> cartItems){

        for (CartItem cartItem: cartItems) {
            try {


                orderDetailRepository.addOrderDetail(new OrderDetail(
                        orders.getOrderID(),
                        cartItem.getItemCode(),
                        cartItem.getQuantity(),
                        cartItem.getDiscount()
                ));


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
