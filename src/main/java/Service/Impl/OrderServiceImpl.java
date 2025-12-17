package Service.Impl;

import Service.OrderService;
import model.dto.Orders;
import repository.Impl.OrderRepositoryImpl;
import repository.OrderRepository;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public void addOrder(Orders orders){

        try {


            orderRepository.addOrder(orders);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
