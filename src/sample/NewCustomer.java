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
public class NewCustomer {

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
    private Button btnSave;

    @FXML
    void btnSaveClicked(MouseEvent event) {
        Connection connect = connectDB();
        String sql = "INSERT INTO `Customers`(`CustFirstName`, `CustLastName`, `CustAddress`, `CustCity`, `CustProv`, `CustPostal`, `CustCountry`, `CustHomePhone`, `CustBusPhone`, `CustEmail`) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            int numRow = statement.executeUpdate();
            if (numRow == 0)
            {
                System.out.println("failed");
            }
            else
            {
                System.out.println("inserted");
            }
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
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'CustomerDetails.fxml'.";

        txtCustomerId.setEditable(false);
        txtCustomerId.setFocusTraversable(false);
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
