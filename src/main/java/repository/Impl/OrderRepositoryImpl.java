package repository.Impl;

import db.DBConnection;
import model.dto.Orders;
import repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public boolean addOrder(Orders orders) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO orders VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,orders.getOrderID());
        preparedStatement.setObject(2,orders.getOrderDate());
        preparedStatement.setObject(3,orders.getCustomerID());


        return  preparedStatement.executeUpdate() > 0;

    }
}
