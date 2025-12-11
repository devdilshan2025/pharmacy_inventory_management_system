package Service.Impl;

import Service.CustomerService;
import Service.PlaceOrderService;
import Service.StockLoginService;
import model.dto.Customer;
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
}
