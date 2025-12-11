package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerRepository {

    ResultSet serchCustomer(String customerID) throws SQLException;
}
