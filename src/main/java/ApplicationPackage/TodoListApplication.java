package ApplicationPackage;
/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Scott Schimpf
 */



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TodoListApplication extends Application{


    ObservableList<ListItem> list = FXCollections.observableArrayList();
    public ListItem CurrentCell = null;
    public int nextId = 0;


    public ObservableList<ListItem> addItem(String date, String description) {
        list.add(new ListItem(nextId, date, description));
        nextId++;
        return list;
    }

    public boolean cellSelectedExist(){
        if (CurrentCell == null) {
            return false;
        }
        return true;
    }

    public void setCurrentCell(ListItem currentCell) {
        this.CurrentCell = currentCell;
    }

    public boolean getCurrentCellStatus() {
        if(CurrentCell.getComplete()) {
            return true;
        }
        return false;
    }

    public void setCurrentCellStatus(boolean status) {
        if(list.contains(this.CurrentCell)) {
            this.CurrentCell.setComplete(status);
        }
    }

    public String getCurrentCellDate() {
        return this.CurrentCell.getDate();
    }

    public String getCurrentCellDescription() {
        return this.CurrentCell.getDescription();
    }

    public ObservableList<ListItem> editSelectedItem(String date, String description) {
        int index = list.indexOf(CurrentCell);
        this.CurrentCell.setDate(date);
        this.CurrentCell.setDescription(description);
        this.list.remove(index);
        this.list.add(index, CurrentCell);
        return this.list;
    }

    //remove item will remove the item with the given ID
    public void removeItem(ListItem item) {

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
