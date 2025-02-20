package Project.application.storeCustomerApp.dao;

import Project.application.storeCustomerApp.model.StoreCustomer;

import java.util.List;


public interface IStoreCustomerDAO {
    //basic crud API services
    StoreCustomer insert(StoreCustomer storeCustomer);
    StoreCustomer update(Long id, StoreCustomer storeCustomer);
    void deleteById(Long id);
    StoreCustomer getById(Long id);
    List<StoreCustomer> getAll();

    void deleteByVatNumber(String vatRegNum);

    StoreCustomer getByVatNumber(String vatRegNum);
    StoreCustomer getByPhoneNumber(String phoneNumber);

    boolean userIdExists(Long id);
    boolean vatNumberExists(String vatRegNum);

}
