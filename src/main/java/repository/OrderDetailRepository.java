package repository;

import model.dto.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailRepository {

    void addOrderDetail(OrderDetail orderDetail) throws SQLException;
}
