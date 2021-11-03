package ApplicationPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class TodoListController implements Initializable {

    public ToggleGroup completeness;

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

    final TodoListApplication application = new TodoListApplication();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateBox.setValue(LocalDate.now());
    }

    @FXML
    private void addItem(Event e) {
        ListViewID.setItems(application.addItem(DateBox.getValue().toString(), DescriptionBox.getText()));
    }

    @FXML
    private void editItem(Event e) {
        if(!application.cellSelectedExist()) {
            addItem(e);
            return;
        }


        ListViewID.setItems(application.editSelectedItem(DateBox.getValue().toString(), DescriptionBox.getText()));


    }

    // This function updates the application with the currently selected cell
    // It also compares the status of the cell to set its current status in the corner
    @FXML
    private void listItemSelected(Event e) {

        ListViewID.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                application.setCurrentCell(ListViewID.getSelectionModel().getSelectedItem());

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
            }
        });
    }

    @FXML
    private void MarkCompleteBtnClick(Event e) {
        if(!application.cellSelectedExist()) {
            return;
        }
        else{
            application.setCurrentCellStatus(true);
        }
    }

    @FXML
    private void MarkIncompleteBtnClick(Event e) {
        if(!application.cellSelectedExist()) {
            return;
        }
        else{
            application.setCurrentCellStatus(false);
        }
    }

}
