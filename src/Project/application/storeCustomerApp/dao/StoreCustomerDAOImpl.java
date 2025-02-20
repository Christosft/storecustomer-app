package Project.application.storeCustomerApp.dao;

import Project.application.storeCustomerApp.model.StoreCustomer;

import java.util.ArrayList;
import java.util.List;

public class StoreCustomerDAOImpl implements IStoreCustomerDAO {
    private static final List<StoreCustomer> customers = new ArrayList<>();
    private static Long id = 1L;

    @Override
    public StoreCustomer insert(StoreCustomer storeCustomer) {
        storeCustomer.setId(id++);
        customers.add(storeCustomer);
        return storeCustomer;
    }

    @Override
    public StoreCustomer update(Long id, StoreCustomer storeCustomer) {
        customers.set(getIndexById(id), storeCustomer);
        return storeCustomer;
    }

    @Override
    public void deleteById(Long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }

    @Override
    public StoreCustomer getById(Long id) {
        int positionToReturn = getIndexById(id);
        return (positionToReturn != -1) ? customers.get(positionToReturn) : null;
    }

    @Override
    public List<StoreCustomer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public void deleteByVatNumber(String vatRegNum) {
        customers.removeIf(customer -> customer.getVatRegNum().equals(vatRegNum));
    }

    @Override
    public StoreCustomer getByVatNumber(String vatRegNum) {
        int positionToReturn = getIndexByVatNumber(vatRegNum);
        return (positionToReturn != -1) ? customers.get(positionToReturn) : null;
    }

    @Override
    public StoreCustomer getByPhoneNumber(String phoneNumber) {
        int positionToReturn = getIndexByPhoneNumber(phoneNumber);
        return (positionToReturn != -1) ? customers.get(positionToReturn) : null;
    }

    @Override
    public boolean userIdExists(Long id) {
        int position = getIndexById(id);
        return position != -1;
    }

    @Override
    public boolean vatNumberExists(String vatRegNum) {
        int position = getIndexByVatNumber(vatRegNum);
        return position != -1;
    }

    private int getIndexById(Long id) {
        int positionToReturn = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(id)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }

    private int getIndexByVatNumber(String vatRegNum) {
        int positionToReturn = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getVatRegNum().equals(vatRegNum)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }

    private int getIndexByPhoneNumber(String phoneNumber) {
        int positionToReturn = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getPhoneNumber().equals(phoneNumber)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }
}
