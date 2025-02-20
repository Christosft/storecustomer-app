package Project.application.storeCustomerApp.mapper;

import Project.application.storeCustomerApp.dto.StoreCustomerInsertDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerReadOnlyDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerUpdateDTO;
import Project.application.storeCustomerApp.model.StoreCustomer;

public class Mapper {

    private Mapper() {

    }

    public static StoreCustomer mapInsertDTOToCustomer(StoreCustomerInsertDTO dto) {
        return new StoreCustomer(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber(), dto.getAddress(), dto.getEmail(), dto.getVatRegNum(),
        dto.getTaxOffice(), dto.getCompanyName());
    }

    public static StoreCustomer mapUpdateDTOToCustomer(StoreCustomerUpdateDTO dto) {
        return new StoreCustomer(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber(), dto.getAddress(), dto.getEmail(), dto.getVatRegNum(),
                dto.getTaxOffice(), dto.getCompanyName());
    }

    public static StoreCustomerReadOnlyDTO mapReadOnlyDTOToCustomer(StoreCustomer storeCustomer) {
        return new StoreCustomerReadOnlyDTO(storeCustomer.getId(), storeCustomer.getFirstname(), storeCustomer.getLastname(), storeCustomer.getPhoneNumber(), storeCustomer.getAddress(), storeCustomer.getEmail(), storeCustomer.getVatRegNum(), storeCustomer.getTaxOffice(), storeCustomer.getCompanyName());
    }
}
