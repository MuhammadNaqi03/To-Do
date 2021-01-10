package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creates a root node by reading the sample.fxml
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        /* We need Scene and stage for fxml.
        Stage can be though of entire window which holds the scene
        and Scene is the place which holds the components.
         */

        Scene todoList = new Scene(root, 500, 400); // creating a scene with root node.
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(todoList);
        primaryStage.show();
    }


    /*
    The main method calls the launch method of the Application class
    which calls the start method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
