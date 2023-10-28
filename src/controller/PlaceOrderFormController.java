package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CartTm;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    public ComboBox cmbCostID;
    public ComboBox itemCodeId;
    public Label dateId;
    public TextField txtIdName;
    public TextField txtIdAddress;
    public TextField txtIdSalary;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtIQtyOnHand;
    public TextField txtQty;
    public TableView tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label lblLastTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadCustomerIDS();
        loadItemCodes();
        loadDate();
    }


    void loadCustomerIDS() {
        ArrayList<String> customerIds = new CustomerController().getCustomerIds();
        cmbCostID.getItems().addAll(customerIds);
    }

    void loadItemCodes(){
        ArrayList<String> itemCodes=new CustomerController().getItemCodes();
        itemCodeId.getItems().addAll(itemCodes);
    }
    void loadDate(){
        Date date = new Date();
        SimpleDateFormat f=new SimpleDateFormat("YYYY-MM-dd");
        dateId.setText(f.format(date));
    }

    public void custIDonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = cmbCostID.getSelectionModel().getSelectedItem().toString();
        txtIdName.setText(CustomerController.searchCustomerById(id).getName());
        txtIdAddress.setText(CustomerController.searchCustomerById(id).getAddress());
        txtIdSalary.setText(String.valueOf(CustomerController.searchCustomerById(id).getSalary()));
    }


    public void itemOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = itemCodeId.getSelectionModel().getSelectedItem().toString();
        txtDescription.setText(CustomerController.searchItemByCode(code).getDescription());
        txtUnitPrice.setText(String.valueOf(CustomerController.searchItemByCode(code).getUnitprice()));
        txtIQtyOnHand.setText(String.valueOf(CustomerController.searchItemByCode(code).getQty()));

    }
    ObservableList<CartTm> cart = FXCollections.observableArrayList();
    public void btnOrderAddOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = qtyForCustomer*unitPrice;
        int qtyOnHand = Integer.parseInt(txtIQtyOnHand.getText());

        if(qtyForCustomer > qtyOnHand){
            new Alert(Alert.AlertType.WARNING,"Invalid Qty....").show();
            return;
        }
        CartTm cartTm = new CartTm((String) itemCodeId.getValue(), description, qtyForCustomer, unitPrice, total);
        cart.add(cartTm);
        tblCart.setItems(cart);
        calculateLastTotal();
    }

    private void calculateLastTotal(){
        double ttl = 0;
        for (CartTm tm:cart){
            ttl+=tm.getTotal();
        }
        lblLastTotal.setText(ttl+"/=");
    }


    public void btnPlaceOrder(ActionEvent actionEvent) {


    }
}








