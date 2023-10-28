package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public TextField txtname;
    public TextField txtid;
    public TextField txtaddress;
    public TextField txtsalary;
    public TableView tableCustomer;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTable();
    }

    public void btnaddonaction(ActionEvent actionEvent){
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        double salary = Double.parseDouble(txtsalary.getText());
        Customer customer = new Customer(id,name,address,salary);
        boolean isAddedCustomer = new CustomerController().addCustomer(customer);
        if(isAddedCustomer){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added Successfully").show();
        }
        loadTable();
    }
    public void btnupdateonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        double salary = Double.parseDouble(txtsalary.getText());
        Customer customer = new Customer(id,name,address,salary);
        boolean isupdated = new CustomerController().updateCustomer(customer);
        if(isupdated){
            new Alert(Alert.AlertType.INFORMATION,"Customer Updated Successfully").show();
        }
        loadTable();
    }


    public void btndeleteonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isdeleted=new CustomerController().deleteCustomer(txtid.getText());
        if (isdeleted){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Delete Failled.....").show();
        }
        loadTable();
    }


    public void btnSearchOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        String id = txtid.getText();
       Customer customer=new CustomerController().searchCustomer(id);
       if (customer!=null){
           txtname.setText(customer.getName());
           txtaddress.setText(customer.getAddress());
           txtsalary.setText(String.valueOf(customer.getSalary()));
       }else {
           new Alert(Alert.AlertType.ERROR,"Customer missing....").show();
       }
    }


    public void loadTable(){
            ObservableList<Customer> allCustomer = new CustomerController().getAllCustomer();
            tableCustomer.setItems(allCustomer);
    }


}




