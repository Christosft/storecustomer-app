package Project.application.storeCustomerApp.service;

import Project.application.storeCustomerApp.dto.StoreCustomerInsertDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerUpdateDTO;
import Project.application.storeCustomerApp.exceptions.CustomerNotFoundException;
import Project.application.storeCustomerApp.exceptions.VatNumberAlreadyExistsException;
import Project.application.storeCustomerApp.model.StoreCustomer;

import java.util.List;

public interface IStoreCustomerService {

    StoreCustomer insertStoreCustomer(StoreCustomerInsertDTO dto)
        throws VatNumberAlreadyExistsException;
    StoreCustomer updateStoreCustomer(StoreCustomerUpdateDTO dto)
        throws VatNumberAlreadyExistsException, CustomerNotFoundException;

    void deleteCustomerById(Long id)
        throws CustomerNotFoundException;

    List<StoreCustomer> getAllCustomers();

    StoreCustomer getCustomerByVat(String vatRegNum)
        throws CustomerNotFoundException;
    StoreCustomer getCustomerByPhoneNumber(String phoneNumber)
            throws CustomerNotFoundException;
    void deleteCustomerByVat(String vatRegNum)
        throws CustomerNotFoundException;
}