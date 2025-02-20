package Project.application.storeCustomerApp.service;

import Project.application.storeCustomerApp.dao.IStoreCustomerDAO;
import Project.application.storeCustomerApp.dto.StoreCustomerInsertDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerUpdateDTO;
import Project.application.storeCustomerApp.exceptions.CustomerNotFoundException;
import Project.application.storeCustomerApp.exceptions.VatNumberAlreadyExistsException;
import Project.application.storeCustomerApp.mapper.Mapper;
import Project.application.storeCustomerApp.model.StoreCustomer;

import java.util.List;

import static Project.application.storeCustomerApp.mapper.Mapper.mapInsertDTOToCustomer;

public class StoreCustomerServiceImpl implements IStoreCustomerService{

    private final IStoreCustomerDAO dao;


    public StoreCustomerServiceImpl(IStoreCustomerDAO dao) {
        this.dao = dao;
    }

    @Override
    public StoreCustomer insertStoreCustomer(StoreCustomerInsertDTO dto) throws VatNumberAlreadyExistsException {
        StoreCustomer storeCustomer;

        try {
            if (dao.vatNumberExists(dto.getVatRegNum())) {
                throw new VatNumberAlreadyExistsException("Customer with VAT number " + dto.getVatRegNum() + " already exists");
            }
            storeCustomer = mapInsertDTOToCustomer(dto);

            System.err.printf("StoreCustomerServiceImpl: %s was inserted", storeCustomer);
            return dao.insert(storeCustomer);
        } catch (VatNumberAlreadyExistsException e) {
            System.err.printf("StoreCustomerServiceImpl logger: customer with VAT number: %s already exists\n", dto.getVatRegNum());
            throw e;
        }
    }

    @Override
    public StoreCustomer updateStoreCustomer (StoreCustomerUpdateDTO dto)
            throws VatNumberAlreadyExistsException, CustomerNotFoundException {
        StoreCustomer storeCustomer;
        StoreCustomer newCustomer;
        try {
            if (!dao.userIdExists(dto.getId())) {
                throw new CustomerNotFoundException("Customer with id: " + dto.getId() + " not found for update.");
            }

            storeCustomer = dao.getById(dto.getId());
            boolean isVatNumberOurOwn = storeCustomer.getVatRegNum().equals(dto.getVatRegNum());
            boolean isVatNumberAlreadyExists = dao.vatNumberExists(dto.getVatRegNum());

            if (isVatNumberAlreadyExists && !isVatNumberOurOwn) {
                throw new VatNumberAlreadyExistsException("Customer with VAT number: " + dto.getVatRegNum()
                        + " already exists and can not be updated.");
            }

            newCustomer = Mapper.mapUpdateDTOToCustomer(dto);
            System.err.printf("MobileContactServiceImpl Logger: %s was updated with new info: %s\n", storeCustomer, newCustomer);
            return dao.update(dto.getId(), newCustomer);
        } catch (CustomerNotFoundException | VatNumberAlreadyExistsException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteCustomerById(Long id) throws CustomerNotFoundException {
        try {
            if (!dao.userIdExists(id)) {
                throw new CustomerNotFoundException("Customer with id: " + id + " not found for delete");
            }
            System.err.printf("StoreCustomerServiceImpl Logger: customer with id: %d was deleted\n", id);
            dao.deleteById(id);
        } catch (CustomerNotFoundException e) {
            System.err.printf("StoreCustomerServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<StoreCustomer> getAllCustomers() {
        return dao.getAll();
    }

    @Override
    public StoreCustomer getCustomerByVat(String vatRegNum) throws CustomerNotFoundException {
        StoreCustomer storeCustomer;
        try {
            storeCustomer = dao.getByVatNumber(vatRegNum);
            if (storeCustomer == null) {
                throw new CustomerNotFoundException("Contact with VAT number: " + vatRegNum + " not found");
            }
            return storeCustomer;
        }catch (CustomerNotFoundException e) {
            System.err.printf("Customer with VAT number: %s was not found to get returned\n", vatRegNum);
            throw e;
        }
    }

    @Override
    public StoreCustomer getCustomerByPhoneNumber(String phoneNumber) throws CustomerNotFoundException {
        StoreCustomer storeCustomer;
        try {
            storeCustomer = dao.getByPhoneNumber(phoneNumber);
            if (storeCustomer == null) {
                throw new CustomerNotFoundException("Contact with VAT number: " + phoneNumber + " not found");
            }
            return storeCustomer;
        }catch (CustomerNotFoundException e) {
            System.err.printf("Customer with VAT number: %s was not found to get returned\n", phoneNumber);
            throw e;
        }
    }

    @Override
    public void deleteCustomerByVat(String vatRegNum) throws CustomerNotFoundException {
        try {
            if (!dao.vatNumberExists(vatRegNum)) {
                throw new CustomerNotFoundException("Customer with VAT number: " + vatRegNum + " not found for delete");
            }
            System.err.printf("StoreCustomerServiceImpl Logger: customer with VAT number: %s was deleted\n", vatRegNum);
            dao.deleteByVatNumber(vatRegNum);
        } catch (CustomerNotFoundException e) {
            System.err.printf("StoreCustomerServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }
    }
}
