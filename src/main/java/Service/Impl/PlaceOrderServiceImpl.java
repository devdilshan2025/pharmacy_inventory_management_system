package Service.Impl;

import Service.*;
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

    OrderService  orderService = new OrderServiceImpl();
    OrderDetailService orderDetailService = new OrderDetailServiceImpl();


    @Override
    public void placeaorder(Orders orders, ObservableList<CartItem> cartItems) {


       orderService.addOrder(orders);

       orderDetailService.addOrderDetail(orders, cartItems);

       stockLoginService.updateItemQuantity(cartItems);
    }
}
