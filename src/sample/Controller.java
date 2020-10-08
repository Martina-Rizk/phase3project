package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Customers> lvCustomers;

    @FXML
    private Button btnAdd;

    @FXML
    void btnAddOnClicked(MouseEvent event) {

        FXMLLoader newPage = new FXMLLoader(getClass().getResource("NewCustomer.fxml"));
        try {
            Parent root = newPage.load();
            // get CustomerDetails controller
            NewCustomer newCustomer = newPage.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New Customer");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert lvCustomers != null : "fx:id=\"lvCustomers\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'sample.fxml'.";

        // connect with travel experts database
        Connection connect = connectDB();

        // create a list for Agents
        ObservableList<Customers> agents = FXCollections.observableArrayList();
        try {
            Statement stmt = connect.createStatement();
            String sql = "select * from Customers";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next())
            {
                agents.add(new Customers(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getString(11)));
            }
            lvCustomers.setItems(agents);   // set agents list to the combobox
            connect.close();            // close the connection to the database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // get the selected item from the ListView
        lvCustomers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader newPage = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
                try {
                    Parent root = newPage.load();
                    // get CustomerDetails controller
                    CustomerDetails customerDetail = newPage.getController();
                    customerDetail.setData(lvCustomers.getSelectionModel().getSelectedItem());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Customer Details");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
