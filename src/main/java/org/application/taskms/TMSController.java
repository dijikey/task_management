package org.application.taskms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.application.core.CoreFile;
import org.application.core.Task;

import java.io.File;

import static org.application.core.GlobalVar.DATA_BASE;
import static org.application.taskms.TMSApplication.stage;

public class TMSController {
    @FXML
    private Pane structurePane;
    @FXML
    private TextField title;
    @FXML
    private CheckBox isComplete;
    @FXML
    private TextArea description;
    @FXML
    private Label info;
    @FXML
    private ListView<String> taskList;

    private Task task;

    @FXML
    protected void onNewFileButton() {
        DATA_BASE.createTask();
        updateTaskList();
    }

    @FXML
    protected void closeApp(){
        stage.close();
    }

    @FXML
    protected void saveDataBase(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveFile = fileChooser.showSaveDialog(stage);

        new CoreFile().saveTaskSystem(saveFile);
    }

    @FXML
    protected void chooseDataBase(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) return;
        new CoreFile().loadTaskSystem(selectedFile);
        updateTaskList();
    }

    @FXML
    protected void onClickOfItem(){
        if (taskList.getSelectionModel().getSelectedIndex() < 0) return;
        if (!structurePane.isVisible()) structurePane.setVisible(true);

        task = DATA_BASE.getTask(taskList.getSelectionModel().getSelectedIndex());
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        isComplete.setSelected(task.isComplete());
        info.setText("%s  |  Task id: %s".formatted(task.getCreatedAt(), task.getId()));
    }

    @FXML
    protected void onClickRemoveButton(){
        DATA_BASE.removeTask(taskList.getSelectionModel().getSelectedIndex());
        updateTaskList();
    }

    @FXML
    protected void updateTaskAttribute(){
        task.setTitle(title.getText());
        task.setDescription(description.getText());
        task.setComplete(isComplete.isSelected());
        updateTaskList();
    }

    private void updateTaskList(){
        taskList.setItems(DATA_BASE.getTaskNames());
    }
}