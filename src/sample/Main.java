package sample;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.IOException;

public class Main extends Application {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Travel Experts Customers");
//        primaryStage.setScene(new Scene(root, 375, 400));
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }


    //
    // Primary Stage
    Stage window;

    // Two scenes
    Scene scene1, scene2;
    // The panes are associated with the respective .fxml files
    private Pane pane1;
    private Pane pane2;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            // Set the window as primary stage
            window = primaryStage;

            // Load the fxml files and their controllers
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.fxml"));
            pane1 = loader.load();
            Controller controller = loader.getController();

            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("CustomerDetails.fxml"));
            pane2 = loader.load();
            CustomerDetails customerDetails = loader.getController();
            customerDetails.setData(controller.selectedCustomer);

            // The scenes are based on what has been loaded from the .fxml files
            Scene scene1 = new Scene(pane1);
            Scene scene2 = new Scene(pane2);

            // Pass reference the each scenes controller
            controller.setScene2(scene2);
            controller.setMain(this);
            customerDetails.setScene1(scene1);
            customerDetails.setMain(this);

            //Display scene 1 at first
            window.setScene(scene1);
            window.setTitle("Travel expert's customers");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // used by the controllers to switch the scenes
    public void setScene(Scene scene){
        window.setScene(scene);
    }
}
