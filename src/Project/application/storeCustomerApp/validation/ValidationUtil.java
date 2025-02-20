package Project.application.storeCustomerApp.validation;

import Project.application.storeCustomerApp.dto.StoreCustomerInsertDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerUpdateDTO;

public class ValidationUtil {

    private ValidationUtil() {

    }

    public static String validateDTO(StoreCustomerInsertDTO insertDTO) {
        String errorResponse = "";

        if (insertDTO.getVatRegNum().length() <= 5) errorResponse += "Vat numbers must have more than five digits.\n";
        if (insertDTO.getPhoneNumber().length() <= 7)
            errorResponse += "Phone numbers must have more than seven digits.\n";
        if (insertDTO.getFirstname().length() <= 3) errorResponse += "Firstnames must have more than three digits.\n";
        if (insertDTO.getLastname().length() <= 2) errorResponse += "Lastnames must have more than two digits.\n";

        return errorResponse;
    }

    public static String validateDTO(StoreCustomerUpdateDTO updateDTO) {
        String errorResponse = "";

        if (updateDTO.getVatRegNum().length() <= 5) errorResponse += "Vat numbers must have more than five digits.\n";
        if (updateDTO.getPhoneNumber().length() <= 7)
            errorResponse += "Phone numbers must have more than seven digits.\n";
        if (updateDTO.getFirstname().length() <= 3) errorResponse += "Firstnames must have more than three digits.\n";
        if (updateDTO.getLastname().length() <= 2) errorResponse += "Lastnames must have more than two digits.\n";

        return errorResponse;
    }
}
