package repository;

import model.dto.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailRepository {

    boolean addOrderDetail(OrderDetail orderDetail) throws SQLException;
}
