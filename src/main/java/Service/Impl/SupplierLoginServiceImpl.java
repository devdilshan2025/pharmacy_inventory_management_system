package Service.Impl;

import Service.SupplierLoginService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.SupplyInfo;
import repository.SupplyRepository;
import repository.Impl.SupplyRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierLoginServiceImpl implements SupplierLoginService {

    SupplyRepository supplyRepository = new SupplyRepositoryImpl();

    @Override
    public void addSuppliers(String supplierID, String name, String contactPerson, String phone, String email, String address, String notes){

        try {


          supplyRepository.addSuppliers(supplierID, name, contactPerson, phone, email, address, notes );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SupplyInfo> getAllSuppliers(){

        ObservableList<SupplyInfo> supplyInfos = FXCollections.observableArrayList();

        try {


            ResultSet resultSet=supplyRepository.getAllSuppliers();
            while (resultSet.next()){
                SupplyInfo supplyInfo =new SupplyInfo(
                        resultSet.getString("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact_Person"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("Address"),
                        resultSet.getString("notes")
                );

                supplyInfos.add(supplyInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return supplyInfos;

    }

    @Override
    public void deleteStockDetails(String supplierID){

        try {


            supplyRepository.deleteStockDetails(supplierID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void updateSupplier(String name, String cntactPerson, String phone, String email, String address, String notes, String supplierID){

        try {

          supplyRepository.updateSupplier(name, cntactPerson, phone, email, address, notes, supplierID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
