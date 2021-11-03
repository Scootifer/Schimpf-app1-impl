package ApplicationPackage;
/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Scott Schimpf
 */



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TodoListApplication extends Application{


    private ArrayList<ListItem> list;
    private int itemCounter;

    //This funtion will make a new ListItem object with the id of itemCounter, then add the
    //new object to the list and increment counter.
    public void addItem(String title, String date, String description) {

    }

    //remove item will remove the item with the given ID
    public void removeItem(int id) {

    }

    //will edit the item with the given ID
    public void editTitle(int id, String title) {

    }

    //will edit the item with the given ID
    public void editDescription(int id, String desc) {

    }

    //editDate will edit the item with the given ID
    public void editDate(int id, String date) {

    }

    //this will return a list of only complete items
    public ArrayList<ListItem> sortComplete(){
        return null; //to get rid of the error
    }

    //this will return a list of only incomplete items
    public ArrayList<ListItem> sortIncomplete(){
        return null; //to get rid of the error
    }

    //This function will be used to revert to showing all items
    //it will simply display the unchanged original list
    public void showAll() {

    }

    // THis function will save the data to a JSON format file using the gson library
    public void save(){

    }

    // THis function will load the data from a JSON format file using the gson library
    public void load(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListGUI.fxml")));

            stage.setScene(scene);
            stage.show();



        } catch(Exception e){
           e.printStackTrace();
        }




    }



}
