package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    /*In order to link between the methods and GUI we have to
    First write the method
    Then Save all changes
    Later go to the Scene Builder and find the On Action in Code section & simply add the method.
     */

    /*We are working with javafx.
     So make sure to import things like ActionEvent,TextField and other elem as javafx one
    not the java.awt*/

    /*@FXML lets us to create elements within controller class to be associated with the GUI
    And always add that to fxid in the corresponding GUI element
     */
    @FXML private TextField newTodo;
    @FXML private ListView<String> taskList;

    /*To save the taskList we will create a text file.
    The tasks will be written on it before exit and
    will be loaded when initiated.
     */
    private String taskFilePath = "listOfTasks.txt"; //We can use absolute path as well
    //Let's create the file object
    private File taskFile = new File(taskFilePath); //From java.io


    //Method for adding newTask
    public void addTask(ActionEvent e){
        String taskToAdd = newTodo.getText();

        //Adding task only if the string is not empty
        if (!taskToAdd.equals("")){
            taskList.getItems().add(taskToAdd);
            //Setting the TextField to empty
            newTodo.setText("");
        }
    }

    //Method for removing task
    public void removeTask(ActionEvent e){
        String itemToRemove = taskList.getSelectionModel().getSelectedItem();

        //Only remove if not null
        if(itemToRemove != null){
            taskList.getItems().remove(itemToRemove);
        }
    }


    //Method for exit class
    public void bye(ActionEvent e){

        List<String> existingTasks = taskList.getItems();

        try {
            //Creating file writer instance with the associated path
            FileWriter flwriter = new FileWriter(taskFilePath);
            for(String task:existingTasks){
                task += "\n";  //creating new line for every tasks
                flwriter.write(task);
            }
            flwriter.close();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }


        System.exit(0); // You can write the any status code as your wish
    }


    //Executed as soon as the components of UI is being loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Scanner tasksToLoad = new Scanner(taskFile);

            while(tasksToLoad.hasNextLine()){
                String task = tasksToLoad.nextLine();
                taskList.getItems().add(task);
            }
            tasksToLoad.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
