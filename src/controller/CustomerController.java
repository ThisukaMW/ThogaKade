package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

public class CustomerController implements CustomerService{
    @Override
    public boolean addCustomer(Customer customer) {
        try {
            PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
            psTm.setObject(1 , customer.getId());
            psTm.setObject(2 , customer.getName());
            psTm.setObject(3 , customer.getAddress());
            psTm.setObject(4 , customer.getSalary());
            if (psTm.executeUpdate() > 0) {
                return true;
            }
        }catch (SQLException  | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean addItem(Item item) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO item VALUES(?,?,?,?)");
            preparedStatement.setObject(1,item.getCode());
            preparedStatement.setObject(2,item.getDescription());
            preparedStatement.setObject(3,item.getUnitprice());
            preparedStatement.setObject(4,item.getQty());
            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE Customer SET name=?,address=?,salary=? WHERE id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getAddress());
        preparedStatement.setObject(3,customer.getSalary());
        preparedStatement.setObject(4,customer.getId());
        return preparedStatement.executeUpdate() >0 ;
    }

    @Override
    public boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE item SET description=?,unitprice=?,qtyonhand=? WHERE code=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1,item.getDescription());
        preparedStatement.setObject(2,item.getUnitprice());
        preparedStatement.setObject(3,item.getQty());
        preparedStatement.setObject(4,item.getCode());
        return preparedStatement.executeUpdate() >0 ;
    }



    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM Customer WHERE id='"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if (resultSet.next()){
            return new Customer(id,resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
        }else {
            return null;
        }
    }

    @Override
    public Item searchItem(String code) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM item WHERE code='"+code+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if(resultSet.next()){
            return new Item(code,resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
        }else{
            return null;
        }
    }


    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String SQL="DELETE FROM Customer WHERE id='"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(SQL) >= 0;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        String SQL="DELETE FROM item WHERE code='"+code+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(SQL) >= 0;
    }


    @Override
    public ObservableList<Customer> getAllCustomer() {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer(resultSet.getString(1),
                        resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
                customersList.add(customer);
            }
            return customersList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAllItem() {
        ObservableList<Item> itemList= FXCollections.observableArrayList();
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()){
                Item item=new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
                itemList.add(item);
            }
            return itemList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<String> getCustomerIds() {
        ArrayList<String> ids = new ArrayList<>();
        String SQL = "SELECT * FROM Customer";
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                ids.add(rst.getString(1));

            }
            return ids;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getItemCodes() {
        ArrayList<String> codes = new ArrayList<>();
        String SQL = "SELECT * FROM Item";
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                codes.add(rst.getString(1));
            }
            return codes;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Customer searchCustomerById(String id) throws SQLException, ClassNotFoundException{
        String SQL="SELECT * FROM Customer WHERE id='"+id+"'";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if (resultSet.next()){
            return new Customer(id,resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
        }else {
            return null;
        }
    }

    public static Item searchItemByCode(String code) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM Item WHERE code='"+code+"'";
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if (resultSet.next()){
            return new Item(code,resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
        }else {
            return null;
        }

    }
}


