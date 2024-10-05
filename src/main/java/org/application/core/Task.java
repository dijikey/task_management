package org.application.core;

import java.time.LocalDate;

public class Task {
    private int id = 0;
    private String title = "New task";
    private String description = "";
    private boolean isComplete = false;
    private String createdAt = LocalDate.now().toString().replaceAll("-", ".");


    Task(int id){
        this.id = id;
    }

    Task(int id, String title, String desc, boolean isComplete, String createdAt){
        this.id = id;
        this.title = title;
        this.description = desc;
        this.isComplete = isComplete;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
