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
import model.Item;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtPrice;
    public TextField txtQty;
    public TableView itemTable;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQty;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadTable();
    }




    public void loadTable(){
        ObservableList<Item> allItem = new CustomerController().getAllItem();
        itemTable.setItems(allItem);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        Item item = new Item(code, description, price, qty);
        boolean isAddedItem = new CustomerController().addItem(item);
        if(isAddedItem){
            new Alert(Alert.AlertType.INFORMATION,"Item Added Successfully").show();
        }
        loadTable();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtCode.getText();
        Item item = new CustomerController().searchItem(code);
        if(item!=null){
            txtDescription.setText(item.getDescription());
            txtPrice.setText(String.valueOf(item.getUnitprice()));
            txtQty.setText(String.valueOf(item.getQty()));
        }else {
            new Alert(Alert.AlertType.ERROR,"Item missing....").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isdeleted = new CustomerController().deleteItem(txtCode.getText());
        if (isdeleted){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted Successfully......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Delete Failled.....").show();
        }
        loadTable();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        Item item = new Item(code, description, price, qty);
        boolean isUpdated = new CustomerController().updateItem(item);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Item Updated Successfully").show();
        }
        loadTable();
    }

}
