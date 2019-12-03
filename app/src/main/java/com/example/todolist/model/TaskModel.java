package com.example.todolist.model;

import android.net.Uri;

import java.util.Date;

public class TaskModel {
    private String title;
    private String description;
    private String image;
    private String deadLine;

    public TaskModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public TaskModel() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return image;
    }

    public String getDeadLine() {
        return this.deadLine;
    }
}
