package repository.Impl;

import db.DBConnection;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {


    @Override
    public ResultSet serchCustomer(String customerID) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM customer WHERE custID = ?");
        pst.setObject(1,customerID);
        return pst.executeQuery();



    }
}
