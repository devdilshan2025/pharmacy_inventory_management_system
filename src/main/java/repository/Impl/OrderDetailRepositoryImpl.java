package repository.Impl;

import db.DBConnection;
import model.dto.OrderDetail;
import repository.OrderDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public ResultSet searchOrderDetails(String orderID, String custID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        // Tables 3ක් Join කරලා දත්ත ගන්නා Query එක
        String sql = "SELECT o.custID, c.custName AS CustomerName, od.OrderID, od.ItemCode, " +
                "od.orderQuantity, od.discount, o.orderDate " +
                "FROM orderdetail od " +
                "JOIN orders o ON od.OrderID = o.OrderID " +
                "JOIN customer c ON o.custID = c.custID " +
                "WHERE od.OrderID = ? OR o.custID = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderID);
        pstm.setString(2, custID);

        return pstm.executeQuery();
    }
}
