package controller;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

import java.sql.SQLException;
import java.util.Observable;

public interface CustomerService {
    boolean addCustomer (Customer customer);

    boolean addItem(Item item);

    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean updateItem(Item item) throws SQLException, ClassNotFoundException;

    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;

    Item searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    ObservableList<Customer> getAllCustomer();

    ObservableList<Item> getAllItem();

}




