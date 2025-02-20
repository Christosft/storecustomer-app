package Project.application.storeCustomerApp.controller;

import Project.application.storeCustomerApp.core.serializer.Serializer;
import Project.application.storeCustomerApp.dao.IStoreCustomerDAO;
import Project.application.storeCustomerApp.dao.StoreCustomerDAOImpl;
import Project.application.storeCustomerApp.dto.StoreCustomerInsertDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerReadOnlyDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerUpdateDTO;
import Project.application.storeCustomerApp.exceptions.CustomerNotFoundException;
import Project.application.storeCustomerApp.exceptions.VatNumberAlreadyExistsException;
import Project.application.storeCustomerApp.mapper.Mapper;
import Project.application.storeCustomerApp.model.StoreCustomer;
import Project.application.storeCustomerApp.service.IStoreCustomerService;
import Project.application.storeCustomerApp.service.StoreCustomerServiceImpl;
import Project.application.storeCustomerApp.validation.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class StoreCustomerController {

    private final IStoreCustomerDAO dao = new StoreCustomerDAOImpl();
    private final IStoreCustomerService service = new StoreCustomerServiceImpl(dao);

    public String insertCustomer(StoreCustomerInsertDTO insertDTO) {
        StoreCustomer storeCustomer;
        StoreCustomerReadOnlyDTO readOnlyDTO;

        try {
            // Validate input data
            String errorVector = ValidationUtil.validateDTO(insertDTO);
            if (!errorVector.isEmpty()) {
                return "Error." + "Validation error\n" + errorVector;
            }
            // If validation is ok insert customer
            storeCustomer = service.insertStoreCustomer(insertDTO);
            readOnlyDTO = Mapper.mapReadOnlyDTOToCustomer(storeCustomer);
            return "OK\n" + Serializer.serializedDTO(readOnlyDTO);
        }catch (VatNumberAlreadyExistsException e){
            return "Error.\n" + e.getMessage() + "\n";
        }
    }

    public String updateCustomer(StoreCustomerUpdateDTO updateDTO) {
        StoreCustomer storeCustomer;
        StoreCustomerReadOnlyDTO readOnlyDTO;

        try {
            // Validate input data
            String errorVector = ValidationUtil.validateDTO(updateDTO);
            if (!errorVector.isEmpty()) {
                return "Error.\n" + "Validation error\n" +errorVector;
            }

            // If validation is ok, insert contact
            storeCustomer = service.updateStoreCustomer(updateDTO);
            readOnlyDTO = Mapper.mapReadOnlyDTOToCustomer(storeCustomer);
            return "OK\n" + Serializer.serializedDTO(readOnlyDTO);
        }catch (VatNumberAlreadyExistsException e){
            return "Error.\n" + e.getMessage() + "\n";
        }
    }

    public String deleteCustomerById(Long id) {
        try {
            service.deleteCustomerById(id);
            return "OK\n Customer is deleted";
        }catch (CustomerNotFoundException e) {
            return "Error\n Error at deletion. Customer not found\n";
        }
    }

    public List<String> getAllContacts() {
        List<StoreCustomer> customers;
        List<String> serializedList = new ArrayList<>();
        StoreCustomerReadOnlyDTO readOnlyDTO;
        String serialized;

        customers = service.getAllCustomers();

        for (StoreCustomer customer : customers) {
            readOnlyDTO = Mapper.mapReadOnlyDTOToCustomer(customer);
            serialized = Serializer.serializedDTO(readOnlyDTO);
            serializedList.add(serialized);
        }
        return serializedList;
    }

    public String getCustomerByVatNumber(String vatRegNum) {
        StoreCustomer storeCustomer;
        StoreCustomerReadOnlyDTO readOnlyDTO;
        try {
            storeCustomer = service.getCustomerByVat(vatRegNum);
            readOnlyDTO = Mapper.mapReadOnlyDTOToCustomer(storeCustomer);
            return "OK\n" + Serializer.serializedDTO(readOnlyDTO);
        }catch (CustomerNotFoundException e) {
            return "Error\n Customer not found \n";
        }
    }

    public String getCustomerByPhoneNumber(String phoneNumber) {
        StoreCustomer storeCustomer;
        StoreCustomerReadOnlyDTO readOnlyDTO;
        try {
            storeCustomer = service.getCustomerByPhoneNumber(phoneNumber);
            readOnlyDTO = Mapper.mapReadOnlyDTOToCustomer(storeCustomer);
            return "OK\n" + Serializer.serializedDTO(readOnlyDTO);
        }catch (CustomerNotFoundException e) {
            return "Error\n Customer not found \n";
        }
    }

    public String deleteCustomerByVatNumber(String vatRegNum) {
        StoreCustomer storeCustomer;
        StoreCustomerReadOnlyDTO readOnlyDTO;
        try {
            storeCustomer = service.getCustomerByVat(vatRegNum);
            readOnlyDTO = Mapper.mapReadOnlyDTOToCustomer(storeCustomer);
            service.deleteCustomerByVat(vatRegNum);
            return "OK\n Customer is deleted" + Serializer.serializedDTO(readOnlyDTO);
        }catch (CustomerNotFoundException e) {
            return "Error\n Error at deletion. Customer not found\n";
        }
    }
}
