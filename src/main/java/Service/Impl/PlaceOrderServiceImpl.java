package Service.Impl;

import Service.CustomerService;
import Service.OrderService;
import Service.PlaceOrderService;
import Service.StockLoginService;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Orders;
import model.dto.StockInfo;

public class PlaceOrderServiceImpl implements PlaceOrderService {

    StockLoginService stockLoginService = new StockLoginServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();


    @Override
    public StockInfo searchItem(String itemCode, Object o) {

        StockInfo item = stockLoginService.searchItem(itemCode, null);

        return item;
    }

    @Override
    public Customer getCustomer(String cusID) {

        Customer customer = customerService.getCustomer(cusID);
        return customer;
    }

    @Override
    public void placeaorder(Orders orders, ObservableList<CartItem> cartItems) {

        OrderService  orderService = new OrderServiceImpl();
        orderService.addOrder(orders);

    }
}
