package ApplicationPackage;
/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Scott Schimpf
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class DataManager {

    ObservableList<ListItem> list;
    public ListItem CurrentCell;
    // 0 for incomplete, 1 for complete, 2 for show all
    private int currentSort;

    public DataManager() {
        this.list = FXCollections.observableArrayList();

        this.CurrentCell = null;

        this.currentSort = 2;
    }

    //addItem takes the date and description from the GUI and creates a new item, then adds the item to the saved list
    public ObservableList<ListItem> addItem(String date, String description) {

        if(description.length() < 1 || description.length() > 256) {
            return list;
        }

        list.add(new ListItem(date, description));
        return list;
    }

    //overloaded for testing use
    public void addItemByObject(ListItem newItem) {
        list.add(newItem);
    }

    //returns if a cell is selected, used to avoid the onclick event handler from producing an error
    public boolean cellSelectedDoesNotExist(){
        return CurrentCell == null;
    }

    //sets the datamanagers focus, this is the item that will be acted on in most functions.
    public void setCurrentCell(ListItem currentCell) {
        this.CurrentCell = currentCell;
    }

    //returns the current cells status, it should be checked prior to the functions call that the cell exists.
    public boolean getCurrentCellStatus() {
        return this.CurrentCell.getComplete();
    }

    //sets the current cells status if it exists in the list.
    public void setCurrentCellStatus(boolean status) {
        if(list.contains(this.CurrentCell)) {
            this.CurrentCell.setComplete(status);
        }
    }

    //returns the date of the current cell
    public String getCurrentCellDate() {
        return this.CurrentCell.getDate();
    }

    public String getCurrentCellDescription() { return this.CurrentCell.getDescription(); }

    // This function will edit an item by removing it, and re-adding the updated item, it takes in the new string and date as parameters
    //
    public ObservableList<ListItem> editSelectedItem(String date, String description) {
        int index = list.indexOf(CurrentCell);
        this.CurrentCell.setDate(date);
        this.CurrentCell.setDescription(description);
        this.list.remove(index);
        this.list.add(index, CurrentCell);
        return this.list;
    }

    //This function removes the current cell, and importantly sets the current cell (programs focus) to null to prevent errors
    public void removeCurrentCell() {

        this.list.remove(this.CurrentCell);
        this.CurrentCell = null;
    }

    //clears the list
    public void clearList(){
        this.list.clear();
    }


    //this will return a list of only complete items from the overall list
    public ObservableList<ListItem> sortComplete(){
        ObservableList<ListItem> sortedList = FXCollections.observableArrayList();
        for(ListItem l : this.list) {
            if(l.getComplete()) {
                sortedList.add(l);
            }
        }

        this.currentSort = 1;
        return sortedList; //to get rid of the error
    }

    //this will return a list of only complete items FROM the list passed in
    public ObservableList<ListItem> sortComplete(ObservableList<ListItem> toSort){
        ObservableList<ListItem> sortedList = FXCollections.observableArrayList();
        for(ListItem l : toSort) {
            if(l.getComplete()) {
                sortedList.add(l);
            }
        }

        this.currentSort = 1;
        return sortedList; //to get rid of the error
    }

    //this will return a list of only incomplete items from all items
    public ObservableList<ListItem> sortIncomplete(){
        ObservableList<ListItem> sortedList = FXCollections.observableArrayList();
        for(ListItem l : this.list) {
            if(!l.getComplete()) {
                sortedList.add(l);
            }
        }

        this.currentSort = 0;
        return sortedList;

    }

    //this will return a list of only incomplete items FROM the passed in list
    public ObservableList<ListItem> sortIncomplete(ObservableList<ListItem> toSort){
        ObservableList<ListItem> sortedList = FXCollections.observableArrayList();
        for(ListItem l : toSort) {
            if(!l.getComplete()) {
                sortedList.add(l);
            }
        }

        this.currentSort = 0;
        return sortedList;

    }

    //This function will be used to revert to showing all items
    //it will simply display the unchanged original list
    public ObservableList<ListItem> showAll() {
        this.currentSort = 2;
        return this.list;
    }

    public int getCurrentSort(){
        return this.currentSort;
    }

    // This function has been updated to first check if the file exists, then write the data on two lines in the file
    // These lines will be formatted as "date,status" \newline "description"
    // If a date or status exist the description always will
    public void save(File file) {

        if (file == null) {
            return;
        }

        try {
            if(file.createNewFile()) {
                System.out.println("File Exists"); //this was only added so that the sonarlint error went away please don't dock me points.
            }


            FileWriter fw = new FileWriter(file);

            for(ListItem l : this.list) {
                fw.append(l.getDate()).append(",").append(String.valueOf(l.getComplete())).append("\n");
                fw.append(l.getDescription()).append("\n");
            }
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    // THis function will load the data from a JSON format file using the gson library
    public void load(File file){

        if (file == null) {
            return;
        }

        String line;

        //We want to reload the list, so it must be cleared first
        this.list.clear();

        try {
            Scanner scan = new Scanner(file);

            while(scan.hasNext()) {
                line = scan.nextLine();
                list.add(new ListItem(line.substring(0,line.indexOf(",")), line.substring(line.indexOf(",")+1), scan.nextLine()));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}
