package Project.application.storeCustomerApp;

import Project.application.storeCustomerApp.controller.StoreCustomerController;
import Project.application.storeCustomerApp.dto.StoreCustomerInsertDTO;
import Project.application.storeCustomerApp.dto.StoreCustomerUpdateDTO;


import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final StoreCustomerController controller = new StoreCustomerController();

    public static void main(String[] args) {
        String choice;

        while (true) {
            printMenu();
            choice = getToken();

            if (choice.equals("q") || (choice.equals("Q")))
            {
                break;
            }

            handleChoice(choice);
        }
        System.out.println("Thank you for using Store Customers App");
    }

    public static void handleChoice(String choice) {
        String firstname;
        String lastname;
        String phoneNumber;
        String address;
        String email;
        String vatRegNum;
        String taxOffice;
        String companyName;
        String response;


        switch (choice) {
            case "1":
                System.out.println("Please insert firstname, lastname, phone number, address, email, Vat number, Tax office, Company name");
                System.out.println("Firstname");
                firstname = getToken();
                System.out.println("Lastname");
                lastname = getToken();
                System.out.println("Phone number");
                phoneNumber = getToken();
                System.out.println("Address");
                address = getToken();
                System.out.println("Email");
                email = getToken();
                System.out.println("VAT number");
                vatRegNum = getToken();
                System.out.println("Tax office");
                taxOffice = getToken();
                System.out.println("Company name");
                companyName = getToken();

                StoreCustomerInsertDTO insertDTO = new StoreCustomerInsertDTO(firstname, lastname, phoneNumber, address, email, vatRegNum, taxOffice, companyName);
                response = controller.insertCustomer(insertDTO);

                if (response.startsWith("OK")) {
                    System.out.println("\nInput succeeded");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("\nInput not succeeded");
                    System.out.println(response.substring(6));
                }
                break;

            case "2":
                System.out.println("Please insert VAT number to update");
                vatRegNum = getToken();
                response = controller.getCustomerByVatNumber(vatRegNum);
                if (response.startsWith("Error")) {
                    System.out.println("Customer not found");
                    System.out.println(response.substring(3));
                    return;
                }
                System.out.println("Input succeeded");
                System.out.println(response.substring(6));
                System.out.println("Please insert existing ID");
                long oldId = Long.parseLong(getToken());
                System.out.println("Please insert new firstname");
                firstname = getToken();
                System.out.println("Please insert new lastname");
                lastname = getToken();
                System.out.println("Please insert new phone number");
                phoneNumber = getToken();
                System.out.println("Please insert new address");
                address = getToken();
                System.out.println("Please insert new Email");
                email = getToken();
                System.out.println("Please insert new Vat number");
                vatRegNum = getToken();
                System.out.println("Please insert new Tax office");
                taxOffice = getToken();
                System.out.println("Please insert new company name");
                companyName = getToken();

                StoreCustomerUpdateDTO storeCustomerUpdateDTO = new StoreCustomerUpdateDTO(oldId, firstname, lastname, phoneNumber, address, email, vatRegNum, taxOffice, companyName);
                response = controller.updateCustomer(storeCustomerUpdateDTO);
                System.out.println(response);

                break;

            case "3":
                System.out.println("Enter VAT number");
                vatRegNum = getToken();
                response = controller.deleteCustomerByVatNumber(vatRegNum);
                if (response.startsWith("OK")) {
                    System.out.println("Successful deletion");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("Unsuccessful deletion");
                    System.out.println(response.substring(6));
                }
                break;

            case "4":
                System.out.println("Enter VAT number");
                vatRegNum = getToken();
                response = controller.getCustomerByVatNumber(vatRegNum);
                if (response.startsWith("OK")) {
                    System.out.println("Successful search");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("Unsuccessful search");
                    System.out.println(response.substring(6));
                }
                break;

            case "5":
                List<String> storeCustomers = controller.getAllContacts();
                if (storeCustomers.isEmpty()) System.out.println("Empty customer list");
                storeCustomers.forEach(System.out::println);
                break;

            default:
                //
                break;
        }
    }

        public static void printMenu () {
            System.out.println("Store customer menu.\n Please make a choice:");
            System.out.println("1. Insert customer");
            System.out.println("2. Update customer");
            System.out.println("3. Delete customer");
            System.out.println("4. Search customer");
            System.out.println("5. Customers List");
            System.out.println("Q/q. Exit");
        }
        private static String getToken () {
            return in.nextLine().trim();
        }
    }
