package ApplicationPackage;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Scott Schimpf
 */

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


    final TodoListApplication application = new TodoListApplication();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateBox.setValue(LocalDate.now());

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
            DateBox.setValue(LocalDate.parse(application.getCurrentCellDate()));

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
        ListViewID.setItems(application.addItem(DateBox.getValue().toString(), DescriptionBox.getText()));
    }

    @FXML
    private void editItem() {
        if(application.cellSelectedDoesNotExist()) {
            addItem();
            return;
        }
        ListViewID.setItems(application.editSelectedItem(DateBox.getValue().toString(), DescriptionBox.getText()));
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
        ListViewID.setItems(application.sortComplete());
    }

    @FXML
    private void IncompleteSortBtnClick() {
        ListViewID.setItems(application.sortIncomplete());
    }

    @FXML
    private void ShowAllBtnClick() {
        ListViewID.setItems(application.showAll());
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
        switch (application.getCurrentSort()) {
            case 1 -> ListViewID.setItems(application.sortComplete());
            case 0 -> ListViewID.setItems(application.sortIncomplete());
            case 2 -> ListViewID.setItems(application.showAll());
        }
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
