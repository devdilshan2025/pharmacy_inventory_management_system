package Service.Impl;

import Service.*;
import db.DBConnection;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Orders;
import model.dto.StockInfo;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderServiceImpl implements PlaceOrderService {

    StockLoginService stockLoginService = new StockLoginServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();


    @Override
    public StockInfo searchItem(String itemCode, Object o) {

        StockInfo item = stockLoginService.searchItem(itemCode, null);

        return item;
    }

    @Override
    public Customer getCustomer(String cusID) {

        Customer customer = customerService.getCustomer(cusID);
        return customer;
    }

    OrderService  orderService = new OrderServiceImpl();
    OrderDetailService orderDetailService = new OrderDetailServiceImpl();


    @Override
    public void placeaorder(Orders orders, ObservableList<CartItem> cartItems) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);

            boolean isAddOrder = orderService.addOrder(orders);
            if (isAddOrder){

                boolean isAddOrderDetail = orderDetailService.addOrderDetail(orders, cartItems);
                if (isAddOrderDetail){

                    boolean isUpdateItemQuantity = stockLoginService.updateItemQuantity(cartItems);
                    if (isUpdateItemQuantity){
                        connection.commit();
                    }
                }
            }

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }







    }
}
