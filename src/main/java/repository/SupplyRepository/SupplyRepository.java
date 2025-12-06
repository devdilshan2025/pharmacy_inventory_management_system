package repository.SupplyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplyRepository {


    ResultSet getAllSuppliers() throws SQLException;

    void addSuppliers(String supplierID, String name, String contactPerson, String phone, String email, String address, String notes) throws SQLException;

    void deleteStockDetails(String supplierID) throws SQLException;

    void updateSupplier(String name, String cntactPerson, String phone, String email, String address, String notes, String supplierID) throws SQLException;
}
