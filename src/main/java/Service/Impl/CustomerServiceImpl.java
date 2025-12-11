package Service.Impl;

import Service.CustomerService;
import model.dto.Customer;
import repository.CustomerRepository;
import repository.Impl.CustomerRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    public Customer getCustomer(String customerID){

        try {
            ResultSet resultSet = customerRepository.serchCustomer(customerID);
            resultSet.next();
            return new Customer(
                    resultSet.getString("custID"),
                    resultSet.getString("custName"),
                    resultSet.getString("custAddress"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"),
                    resultSet.getString("postalCode")
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
