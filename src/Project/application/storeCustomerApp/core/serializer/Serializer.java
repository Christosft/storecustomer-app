package Project.application.storeCustomerApp.core.serializer;

import Project.application.storeCustomerApp.dto.StoreCustomerReadOnlyDTO;

public class Serializer {

    private Serializer() {

    }

    public static String serializedDTO (StoreCustomerReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Firstname: " + readOnlyDTO.getFirstname() + ", Lastname: " + readOnlyDTO.getLastname()
                + ", Phone Number: " + readOnlyDTO.getPhoneNumber() + ", Address: " + readOnlyDTO.getAddress() + ", Email: " + readOnlyDTO.getEmail() +
                ", VAT number: " + readOnlyDTO.getVatRegNum() + ", Tax Office: " + readOnlyDTO.getTaxOffice() + ", Company name: " + readOnlyDTO.getCompanyName();
    }
}
