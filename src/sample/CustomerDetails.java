package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class CustomerDetails {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCustomerId;

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
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    void btnEditClicked(MouseEvent event) {
        enableEditing();
    }

    @FXML
    void btnSaveClicked(MouseEvent event) {
        Connection connect = connectDB();
        String sql = "UPDATE `Customer` SET `CustFirstName`=?, `CustLastName`=?, `CustAddress`=?, `CustCity`=?, `CustProv`=?, " +
                "`CustPostal`=?, `CustCountry`=?, `CustHomePhone`=?, `CustBusPhone`=?, `CustEmail`=? WHERE CustomerId=?";
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, txtFirstName.getText());
            statement.setString(2, txtLastName.getText());
            statement.setString(3, txtAddress.getText());
            statement.setString(4, txtCity.getText());
            statement.setString(5, txtProvince.getText());
            statement.setString(6, txtPostCode.getText());
            statement.setString(7, txtCountry.getText());
            statement.setString(8, txtHomePhone.getText());
            statement.setString(9, txtBusPhone.getText());
            statement.setString(10, txtEmail.getText());
            statement.setInt(11, Integer.parseInt(txtCustomerId.getText()));
            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
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
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'CustomerDetails.fxml'.";

        txtCustomerId.setEditable(false);
        txtCustomerId.setFocusTraversable(false);

        disableEditing();
    }

    public void setData(Customers customer){
        txtCustomerId.setText(String.valueOf(customer.getCustomerID()));
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

    public void enableEditing(){
        txtFirstName.setEditable(true);
        txtFirstName.setFocusTraversable(true);
        txtLastName.setEditable(true);
        txtAddress.setEditable(true);
        txtCity.setEditable(true);
        txtProvince.setEditable(true);
        txtCountry.setEditable(true);
        txtPostCode.setEditable(true);
        txtHomePhone.setEditable(true);
        txtBusPhone.setEditable(true);
        txtEmail.setEditable(true);
    }

    public void disableEditing(){
        txtFirstName.setEditable(false);
        txtFirstName.setFocusTraversable(false);
        txtLastName.setEditable(false);
        txtLastName.setFocusTraversable(false);
        txtAddress.setEditable(false);
        txtAddress.setFocusTraversable(false);
        txtCity.setEditable(false);
        txtCity.setFocusTraversable(false);
        txtProvince.setEditable(false);
        txtProvince.setFocusTraversable(false);
        txtCountry.setEditable(false);
        txtCountry.setFocusTraversable(false);
        txtPostCode.setEditable(false);
        txtPostCode.setFocusTraversable(false);
        txtHomePhone.setEditable(false);
        txtHomePhone.setFocusTraversable(false);
        txtBusPhone.setEditable(false);
        txtBusPhone.setFocusTraversable(false);
        txtEmail.setEditable(false);
        txtEmail.setFocusTraversable(false);
    }
    private Connection connectDB()
    {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "martina", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
