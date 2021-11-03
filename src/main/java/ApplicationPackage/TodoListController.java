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

    public ListItem CurrentCell;
    public int nextId = 0;

    ObservableList<ListItem> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateBox.setValue(LocalDate.now());
    }

    @FXML
    private void addItem(Event e) {
        list.add(new ListItem(nextId, DateBox.getValue().toString(), DescriptionBox.getText()));
        ListViewID.setItems(list);
        nextId++;
    }

    @FXML
    private void listItemSelected(Event e) {

        ListViewID.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CurrentCell = ListViewID.getSelectionModel().getSelectedItem();

                if(CurrentCell.getComplete() == true) {
                    MarkCompleteBtn.setSelected(true);
                    MarkCompleteBtn.setToggleGroup(completeness);
                }
                else {
                    MarkCompleteBtn.setSelected(false);
                    MarkCompleteBtn.setToggleGroup(completeness);
                }
            }
        });
    }

}
