package Controller.SupplierController;

import javafx.collections.ObservableList;
import model.dto.SupplyInfo;

public interface SupplierLoginService {

     void addSuppliers(String text, String txtNameText, String txtContactPersonText, String txtPhoneText, String txtEmailText, String txtAddressText, String txtNotesText);

     ObservableList<SupplyInfo> getAllSuppliers();

    void deleteStockDetails(String supplierID);

    void updateSupplier(String text, String txtContactPersonText, String txtPhoneText, String txtEmailText, String txtAddressText, String txtNotesText, String txtSupplierIDText);
}
