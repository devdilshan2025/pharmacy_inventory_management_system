package repository.Impl;

import db.DBConnection;
import model.dto.OrderDetail;
import repository.OrderDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO orderdetail VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,orderDetail.getOrderID());
        preparedStatement.setObject(2,orderDetail.getItemCode());
        preparedStatement.setObject(3,orderDetail.getOrderQty());
        preparedStatement.setObject(4,orderDetail.getDiscount());


       return preparedStatement.executeUpdate() > 0;

    }
}
