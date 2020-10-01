package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDetails {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtPostCode;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtHomePhone;

    @FXML
    private TextField txtBusPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    void initialize() {
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtProvince != null : "fx:id=\"txtProvince\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtPostCode != null : "fx:id=\"txtPostCode\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtCountry != null : "fx:id=\"txtCountry\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtHomePhone != null : "fx:id=\"txtHomePhone\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtBusPhone != null : "fx:id=\"txtBusPhone\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'CustomerDetails.fxml'.";

    }

    public void setData(Customers customer){
        txtFirstName.setText(customer.getCustFirstName());
        txtLastName.setText(customer.getCustLastName());
        txtAddress.setText(customer.getCustAddress());
        txtCity.setText(customer.getCustCity());
        txtProvince.setText(customer.getCustProv());
        txtCountry.setText(customer.getCustCountry());
        txtPostCode.setText(customer.getCustPostal());
        txtHomePhone.setText(customer.getCustHomePhone());
        txtBusPhone.setText(customer.getCustBusPhone());
        txtEmail.setText(customer.getCustEmail());
    }
}
