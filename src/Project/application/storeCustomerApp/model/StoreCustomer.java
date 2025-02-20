package Project.application.storeCustomerApp.model;

import java.util.Objects;

public class StoreCustomer extends AbstractEntity{

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;
    private String email;
    private String vatRegNum;
    private String taxOffice;
    private String companyName;

    public StoreCustomer() {

    }

    public StoreCustomer(Long id, String firstname, String lastname, String phoneNumber, String address, String email, String vatRegNum, String taxOffice, String companyName) {
        setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.vatRegNum = vatRegNum;
        this.taxOffice = taxOffice;
        this.companyName = companyName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVatRegNum() {
        return vatRegNum;
    }

    public void setVatRegNum(String vatRegNum) {
        this.vatRegNum = vatRegNum;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Firstname: " + firstname + ", Lastname: " + lastname + ", Phone Number: " + phoneNumber + ", " +
                "Address: " + address + ", Email: " + email + ", Tax Office: " + taxOffice + ", VAT Number: " + vatRegNum + ", Company name: " + companyName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof StoreCustomer that))
            return  false;
        return Objects.equals(getFirstname(), that.getFirstname()) && Objects.equals(getLastname(), that.getLastname())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber()) && Objects.equals(getAddress(), that.getAddress())
                && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getTaxOffice(), that.getTaxOffice())
                && Objects.equals(getVatRegNum(), that.getVatRegNum()) && Objects.equals(getCompanyName(), that.getCompanyName());

    }
    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getPhoneNumber(), getAddress(), getEmail(), getTaxOffice(), getVatRegNum(), getCompanyName());
    }
}
