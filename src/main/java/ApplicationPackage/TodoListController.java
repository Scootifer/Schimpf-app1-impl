package ApplicationPackage;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Scott Schimpf
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class TodoListController implements Initializable {

    public ToggleGroup completeness;
    public ToggleGroup sort;

    @FXML
    public DatePicker DateBox;
    @FXML
    public TextField DescriptionBox;
    @FXML
    public Button AddBtn;
    @FXML
    public Button SaveBtn;
    @FXML
    public Button LoadButton;
    @FXML
    public RadioButton ShowAllBtn;
    @FXML
    public RadioButton SortCompleteBtn;
    @FXML
    public RadioButton SortIncompleteBtn;
    @FXML
    public Button RemoveBtn;
    @FXML
    public RadioButton MarkCompleteBtn;
    @FXML
    public RadioButton MarkIncompleteBtn;
    @FXML
    public ListView<ListItem> ListViewID;
    @FXML
    public Button EditBtn;
    @FXML
    public Button ClearListBtn;


    final DataManager application = new DataManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // Here the onMouseClick event is updated with a custom event
        // First it checks that a cell was actually selected
        // Next it sets the fields to the value of the item for editing
        // Finally it sets the radio buttons accordingly7
        ListViewID.setOnMouseClicked(event -> {
            application.setCurrentCell(ListViewID.getSelectionModel().getSelectedItem());
            if(application.cellSelectedDoesNotExist()){
                return;
            }

            //sets desc and date fields for editing
            DescriptionBox.setText(application.getCurrentCellDescription());
            String ld = application.getCurrentCellDate();
            if(ld.equals(" ")) {
                DateBox.setValue(null);
            }
            else {
                DateBox.setValue(LocalDate.parse(ld));
            }


            //completeness radio button logic
            if(!MarkCompleteBtn.isSelected() && !MarkIncompleteBtn.isSelected()) {
                MarkIncompleteBtn.fire();
            }
            else if(application.getCurrentCellStatus() && !MarkCompleteBtn.isSelected()) {
                MarkCompleteBtn.fire();
            }
            else if (!application.getCurrentCellStatus() && MarkCompleteBtn.isSelected()){
                MarkIncompleteBtn.fire();
            }
        });
    }

    @FXML
    private void addItem() {
        String date = " ";
        try {
            date = DateBox.getValue().toString();
        }catch (NullPointerException ignored) {

        }

        ObservableList<ListItem> newList = application.addItem(date, DescriptionBox.getText());
        RefreshList(newList);
    }

    @FXML
    private void editItem() {
        if(application.cellSelectedDoesNotExist()) {
            addItem();
            return;
        }

        String date = " ";
        try {
            date = DateBox.getValue().toString();
        }catch (NullPointerException ignored) {

        }

        ObservableList<ListItem> newList = application.editSelectedItem(date, DescriptionBox.getText());
        RefreshList(newList);
    }


    @FXML
    private void MarkCompleteBtnClick() {
        if(application.cellSelectedDoesNotExist()) {
            return;
        }

        application.setCurrentCellStatus(true);

    }

    @FXML
    private void MarkIncompleteBtnClick() {
        if(application.cellSelectedDoesNotExist()) {
            return;
        }

        application.setCurrentCellStatus(false);

    }

    @FXML
    private void CompleteSortBtnClick() {

        ObservableList<ListItem> newList = application.sortComplete();
        ListViewID.setItems(newList);
    }

    @FXML
    private void IncompleteSortBtnClick() {
        ObservableList<ListItem> newList = application.sortIncomplete();
        ListViewID.setItems(newList);
    }

    @FXML
    private void ShowAllBtnClick() {
        ObservableList<ListItem> newList = application.showAll();
        ListViewID.setItems(newList);
    }

    @FXML
    private void RemoveBtnClick() {
        if(application.cellSelectedDoesNotExist()) {
            return;
        }
        application.removeCurrentCell();
        RefreshList();
    }

    @FXML
    private void RefreshList() {
        ObservableList<ListItem> displayList = FXCollections.observableArrayList();

        switch (application.getCurrentSort()) {
            case 1 -> displayList = application.sortComplete();
            case 0 -> displayList = application.sortIncomplete();
            case 2 -> displayList = application.showAll();
        }
        ListViewID.setItems(displayList);
    }

    @FXML
    private void RefreshList(ObservableList<ListItem> filteredList) {
        ObservableList<ListItem> displayList = FXCollections.observableArrayList();

        switch (application.getCurrentSort()) {
            case 1 -> displayList = application.sortComplete(filteredList);
            case 0 -> displayList = application.sortIncomplete(filteredList);
            case 2 -> displayList = application.showAll();
        }
        ListViewID.setItems(displayList);
    }

    @FXML
    private void LoadBtnClick() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(LoadButton.getScene().getWindow());
        application.load(file);
        RefreshList();

    }
    @FXML
    private void SaveBtnClick() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(LoadButton.getScene().getWindow());
        application.save(file);

    }

    @FXML
    private void ClearListBtnClick(){
        application.clearList();
        RefreshList();
    }

}
