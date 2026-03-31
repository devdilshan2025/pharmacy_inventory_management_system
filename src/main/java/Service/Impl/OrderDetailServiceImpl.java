package Service.Impl;

import Service.OrderDetailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.OrderDetail;
import model.dto.OrderDetailShowDTO;
import model.dto.Orders;
import repository.Impl.OrderDetailRepositoryImpl;
import repository.OrderDetailRepository;

import java.sql.ResultSet;
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

    @Override
    public ObservableList<OrderDetailShowDTO> getAllOrderDetails(String orderId, String custId) {
        ObservableList<OrderDetailShowDTO> detailsList = FXCollections.observableArrayList();

        try {
            // Repository එකේ search method එකට කතා කරනවා
            ResultSet rs = orderDetailRepository.searchOrderDetails(orderId, custId);

            while (rs.next()) {
                detailsList.add(new OrderDetailShowDTO(
                        rs.getString("custID"),        // orders table එකෙන්
                        rs.getString("CustomerName"), // SQL එකේ අපි දීපු Alias එක
                        rs.getString("OrderID"),     // orderdetail table එකෙන්
                        rs.getString("ItemCode"),    // orderdetail table එකෙන්
                        rs.getInt("orderQuantity"),  // orderdetail table එකෙන්
                        rs.getDouble("discount"),    // orderdetail table එකෙන්
                        rs.getString("orderDate")     // orders table එකෙන්
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error loading order details: " + e.getMessage());
        }

        return detailsList;
    }

}
