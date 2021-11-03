package ApplicationPackage;
public class ListItem {

    private int id;
    private String date;
    private String description;
    private boolean complete;

    public ListItem(int id, String date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.complete = false;

    }

    public void setId(int id){
        this.id = id;
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

    public int getId() {
        return this.id;
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
