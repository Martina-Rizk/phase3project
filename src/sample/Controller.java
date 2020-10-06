package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller {
    public Customers selectedCustomer = new Customers();

    private Scene scene2;
    private Main main;

    public void setMain(Main main){
        this.main = main;
    }
    public void setScene2(Scene scene2){
        this.scene2 = scene2;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private ListView<Customers> lvCustomers;

    @FXML
    void initialize() {
        assert lvCustomers != null : "fx:id=\"lvCustomers\" was not injected: check your FXML file 'sample.fxml'.";

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
            lvCustomers.setItems(agents);   // set agents list to the combo box
            connect.close();            // close the connection to the database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // get the selected item from the ListView
        lvCustomers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedCustomer = lvCustomers.getSelectionModel().getSelectedItem();
                main.setScene(scene2);
//                FXMLLoader newPage = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
//                try {
//                    Parent root = newPage.load();
//                    // get CustomerDetails controller
//                    CustomerDetails customerDetail = newPage.getController();
//                    customerDetail.setData(lvCustomers.getSelectionModel().getSelectedItem());
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root));
//                    stage.setTitle("Customer Details");
//                    stage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private Connection connectDB()
    {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "martina", "password");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            c = DriverManager.getConnection("jdbc:sqlserver:localhost/sqlexpress;database=TravelExperts;loginTimeout=30");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
