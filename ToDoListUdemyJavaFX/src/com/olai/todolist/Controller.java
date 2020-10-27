package com.olai.todolist;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;

    private Predicate<TodoItem> wantAllitems;
    private Predicate<TodoItem> wantTodaysItems;

    public void initialize(){

//        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for Johnny", LocalDate.of(2019, Month.OCTOBER, 15));
//        TodoItem item2 = new TodoItem("Doctor's Appointment", "See Dr. Smith at 123 Main Street. Bring paperwork", LocalDate.of(2019, Month.NOVEMBER, 15));
//        TodoItem item3 = new TodoItem("Finish design proposal for client", "I promised Mike i'd email website mockups by Friday 22nd October", LocalDate.of(2019, Month.OCTOBER, 22));
//        TodoItem item4 = new TodoItem("Pickup Doung at the train station", "Doug's arriving on March 23 on the 5:00 train", LocalDate.of(2019, Month.MARCH, 23));
//        TodoItem item5 = new TodoItem("Pickup dry cleaning", "The clothes should be ready by Wednesday", LocalDate.of(2019, Month.OCTOBER, 25));
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });



        listContextMenu.getItems().addAll(deleteMenuItem);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadLine()));

                }
            }
        });

        wantAllitems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return (item.getDeadLine().equals(LocalDate.now()));
            }
        };



        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllitems);




        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList,
                new Comparator<TodoItem>() {
                    @Override
                    public int compare(TodoItem o1, TodoItem o2) {
                        return o1.getDeadLine().compareTo(o2.getDeadLine());
                    }
                });

        //todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();



        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
                ListCell<TodoItem> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(TodoItem todoItem, boolean b) {
                        super.updateItem(todoItem, b);
                        if (b){
                            setText(null);
                        }else {
                            setText(todoItem.getShortDescription());
                            if (todoItem.getDeadLine().isBefore(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.RED);
                            }else if (todoItem.getDeadLine().equals(LocalDate.now().plusDays(1))){
                                setTextFill(Color.ORANGE);
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty){
                                cell.setContextMenu(null);
                            }else {
                                cell.setContextMenu(listContextMenu);
                            }

               });

                return cell;
            }
        });
        // Set the cell factory on listview -> todolistview
        // Pass an anonymous class that implements the callback interface(part of JavaFX api).
        // 2-type parameters:
        // 1. Type of argument provided by the call method.(single method) -> ListView Controller
        // 2. Type that is returned from the call method -> instance of the class cell?

    }


    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
        //    todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            todoListView.getSelectionModel().select(newItem);
        }

    }


    @FXML
    public void handleKeyPressed(KeyEvent keyEvent){
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            if (keyEvent.getCode().equals(KeyCode.DELETE)){
                deleteItem(selectedItem);
            }
        }
    }

    @FXML
    public void handleClickListView(){ // Tracks which item is selected
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadLine().toString());

//        System.out.println("Selected item " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadLine().toString());
//        itemDetailsTextArea.setText(sb.toString());

    }

    public void deleteItem(TodoItem item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)){
            TodoData.getInstance().deleteTodoItem(item);
        }


    }

    @FXML
    public void handleFilterButton(){
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();

        if (filterToggleButton.isSelected()){
            filteredList.setPredicate(wantTodaysItems);
            if (filteredList.isEmpty()){
                itemDetailsTextArea.clear();
                deadlineLabel.setText("");
            } else if(filteredList.contains(selectedItem)){
                todoListView.getSelectionModel().select(selectedItem);
            } else{
                todoListView.getSelectionModel().selectFirst();
            }

        } else {
            filteredList.setPredicate(wantAllitems);
            todoListView.getSelectionModel().select(selectedItem);
        }
    }

    @FXML
    public void handleExit(){
        Platform.exit();
    }




}
