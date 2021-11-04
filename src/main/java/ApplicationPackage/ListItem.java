package ApplicationPackage;
/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Scott Schimpf
 */

public class ListItem {

    private String date;
    private String description;
    private boolean complete;

    public ListItem(String date, String description) {
        this.date = date;
        this.description = description;
        this.complete = false;

    }

    public ListItem(String date, String complete, String description) {
        this.date = date;
        this.description = description;
        this.complete = Boolean.parseBoolean(complete);

    }


    //This function will be used to reset the date of the item.
    public void setDate(String date){
        this.date = date;
    }

    //This function will be used to reset the description of the item.
    public void setDescription(String description){
        this.description = description;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }



    //This function will be used to reset the date of the item.
    public String getDate(){
        return this.date;
    }

    //This function will be used to reset the description of the item.
    public String getDescription(){
        return this.description;
    }

    public boolean getComplete() {
        return this.complete;
    }

    @Override
    public String toString() {
        return String.format("Date: %-20s Description: %-256s", this.date, this.description);
    }

}
