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

    public boolean addOrderDetail(Orders orders, ObservableList<CartItem> cartItems) {

        boolean isAdd = false;


        for (CartItem cartItem : cartItems) {
            try {


                isAdd = orderDetailRepository.addOrderDetail(new OrderDetail(
                        orders.getOrderID(),
                        cartItem.getItemCode(),
                        cartItem.getQuantity(),
                        cartItem.getDiscount()
                ));

                if (isAdd == false) {

                    break;
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isAdd;

    }

}
