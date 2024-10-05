package org.application.core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void createTask() {
        this.tasks.add(new Task(tasks.size()));
    }

    public void createTask(int id, String title, String desc, boolean isComplete, String createdAt) {
        this.tasks.add(new Task(id, title, desc, isComplete, createdAt));
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public Task getTask(int id){
        return this.tasks.get(id);
    }

    public void removeTask(int id){
        tasks.remove(id);
        ArrayList<Task> _tasks = new ArrayList<>(tasks);
        tasks.clear();
        for (Task task : _tasks){
            task.setId(tasks.size());
            tasks.add(task);
        }
    }

    public ObservableList<String> getTaskNames(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Task task : tasks){
            if (task.isComplete()) list.add("✔ " + task.getTitle());
            else list.add(task.getTitle());
        }
        return list;
    }

    protected void clearTaskList(){
        this.tasks.clear();
    }
}
