package ApplicationPackage;
/*
 *  UCF COP3330 Fall 2021 Application 1 Solution
 *  Copyright 2021 Scott Schimpf
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestClass {

    @Test
    void TestAdd(){

        DataManager dm = new DataManager();
        ObservableList<ListItem> list = FXCollections.observableArrayList();

        list.add(new ListItem("2021-11-03", "1"));
        ObservableList<ListItem> testList = dm.addItem("2021-11-03", "1");

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, testList.size()),
                () -> Assertions.assertEquals(list.get(0), testList.get(0))
        );

    }

    @Test
    void TestEdit() {
        DataManager dm = new DataManager();
        ObservableList<ListItem> EXPECTED_LIST = FXCollections.observableArrayList();

        ListItem newItem = new ListItem("2021-11-03", "2");
        EXPECTED_LIST.add(newItem);

        dm.addItem("2021-11-03", "1");

        dm.setCurrentCell(dm.showAll().get(0)); //set the current cell to the first cell
        ObservableList<ListItem> testList = dm.editSelectedItem("2021-11-03", "2");

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, testList.size()),
                () -> Assertions.assertEquals(EXPECTED_LIST.get(0), testList.get(0))
        );

    }

    @Test
    void TestSortComplete() {
        DataManager dm = new DataManager();
        ObservableList<ListItem> EXPECTED_LIST = FXCollections.observableArrayList();

        ListItem newItem = new ListItem("2021-11-03", "3");
        newItem.setComplete(true);
        EXPECTED_LIST.add(newItem);

        dm.addItem("2021-11-03", "2");
        newItem = new ListItem("2021-11-03", "3");
        newItem.setComplete(true);
        dm.addItemByObject(newItem);

        ObservableList<ListItem> sortedList = dm.sortComplete();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, sortedList.size()),
                () -> Assertions.assertEquals(EXPECTED_LIST.get(0), sortedList.get(0))
        );

    }

    @Test
    void TestSortIncomplete() {
        DataManager dm = new DataManager();
        ObservableList<ListItem> EXPECTED_LIST = FXCollections.observableArrayList();

        ListItem newItem = new ListItem("2021-11-03", "3");
        EXPECTED_LIST.add(newItem);

        dm.addItem("2021-11-03", "3");
        newItem = new ListItem("2021-11-03", "2");
        newItem.setComplete(true);
        dm.addItemByObject(newItem);

        ObservableList<ListItem> sortedList = dm.sortIncomplete();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, sortedList.size()),
                () -> Assertions.assertEquals(EXPECTED_LIST.get(0), sortedList.get(0))
        );

    }

    @Test
    void TestShowAll() {
        DataManager dm = new DataManager();
        ObservableList<ListItem> EXPECTED_LIST = FXCollections.observableArrayList();


        ListItem newItem = new ListItem("2021-11-03", "2");
        EXPECTED_LIST.add(newItem);

        newItem = new ListItem("2021-11-03", "3");
        newItem.setComplete(true);
        EXPECTED_LIST.add(newItem);


        dm.addItem("2021-11-03", "2");
        newItem = new ListItem("2021-11-03", "3");
        newItem.setComplete(true);
        dm.addItemByObject(newItem);


        ObservableList<ListItem> sortedList = dm.showAll();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, sortedList.size()),
                () -> Assertions.assertEquals(EXPECTED_LIST.get(0), sortedList.get(0)),
                () -> Assertions.assertEquals(EXPECTED_LIST.get(1), sortedList.get(1))
        );

    }

    @Test
    void TestSave() throws FileNotFoundException {
        DataManager dm = new DataManager();
        File test_output = new File("src/test/resources/test_output.txt");

        dm.addItem("2021-11-03", "1");
        dm.save(test_output);

        Scanner user = new Scanner(test_output);
        Scanner EXPECTED = new Scanner(new File("src/test/resources/EXPECTED.txt"));

        Assertions.assertAll(
                () -> Assertions.assertEquals(EXPECTED.nextLine(), user.nextLine()),
                () -> Assertions.assertEquals(EXPECTED.nextLine(), user.nextLine())
        );

    }

    @Test
    void TestLoad() {
        DataManager dm = new DataManager();
        File load_file = new File("src/test/resources/EXPECTED.txt");
        dm.load(load_file);

        ObservableList<ListItem> EXPECTED_LIST = FXCollections.observableArrayList();
        ListItem newItem = new ListItem("2021-11-03", "1");
        EXPECTED_LIST.add(newItem);


        Assertions.assertAll(
                () -> Assertions.assertEquals(EXPECTED_LIST.get(0), dm.showAll().get(0))
        );

    }


}