package Service;

import model.dto.Customer;
import model.dto.StockInfo;

public interface PlaceOrderService {

     StockInfo searchItem(String itemCode, Object o);

    Customer getCustomer(String cusID);
}
