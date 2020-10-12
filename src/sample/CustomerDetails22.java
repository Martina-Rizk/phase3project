package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomerDetails22 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab tabPersonalInfo;

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
    private TextField txtCustomerId;

    @FXML
    private Button btnDelete;

    @FXML
    void btnDeleteClicked(MouseEvent event) {
        Connection connect = connectDB();
        String sql = "DELETE FROM `customers` WHERE CustomerId=?";
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(txtCustomerId.getText()));
            int numRow = statement.executeUpdate();
            if (numRow == 0)
            {
                System.out.println("failed");
            }
            else
            {
                System.out.println("Deleted");
            }
            connect.close();
            Controller.stage.close();
            //Controller.DisplayCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnEditClicked(MouseEvent event) {
        enableEditing();
    }

    @FXML
    void btnSaveClicked(MouseEvent event) {
        Connection connect = connectDB();
        String sql = "UPDATE `customers` SET `CustFirstName`=?,`CustLastName`=?,`CustAddress`=?,`CustCity`=?," +
                "`CustProv`=?,`CustPostal`=?,`CustCountry`=?,`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=?" +
                " WHERE CustomerId=?";
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, txtFirstName.getText());
            stmt.setString(2, txtLastName.getText());
            stmt.setString(3, txtAddress.getText());
            stmt.setString(4, txtCity.getText());
            stmt.setString(5, txtProvince.getText());
            stmt.setString(6, txtPostCode.getText());
            stmt.setString(7, txtCountry.getText());
            stmt.setString(8, txtHomePhone.getText());
            stmt.setString(9, txtBusPhone.getText());
            stmt.setString(10, txtEmail.getText());
            stmt.setInt(11, Integer.parseInt(txtCustomerId.getText()));
            //stmt.setInt(12, Integer.parseInt(tfCustomerId.getText()));
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                System.out.println("failed");
            }
            else
            {
                System.out.println("updated");
            }
            connect.close();
            Controller.stage.close();
            // main.setScene(scene1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disableEditing();
    }

    @FXML
    void initialize() {
        assert tabPersonalInfo != null : "fx:id=\"tabPersonalInfo\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtProvince != null : "fx:id=\"txtProvince\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtPostCode != null : "fx:id=\"txtPostCode\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtCountry != null : "fx:id=\"txtCountry\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtHomePhone != null : "fx:id=\"txtHomePhone\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtBusPhone != null : "fx:id=\"txtBusPhone\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'CustomerDetails22.fxml'.";

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
        btnSave.setVisible(true);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
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
        btnSave.setVisible(false);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);
    }
    private Connection connectDB()
    {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "martina", "password");
            // next 2 lines for connect with SSMS
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // c = DriverManager.getConnection("jdbc:sqlserver:localhost/sqlexpress;database=travelexperts;loginTimeout=30");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
