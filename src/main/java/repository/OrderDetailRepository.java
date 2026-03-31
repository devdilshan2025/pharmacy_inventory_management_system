package repository;

import model.dto.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailRepository {
    boolean addOrderDetail(OrderDetail orderDetail) throws SQLException;


    ResultSet searchOrderDetails(String orderID, String custID) throws SQLException;
}