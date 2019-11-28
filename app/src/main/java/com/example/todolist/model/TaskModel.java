package com.example.todolist.model;

import android.net.Uri;

import java.util.Date;

public class TaskModel {
    private String title;
    private String description;
    private Uri image;
    private Date deadLine;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDeadLine() {
        return this.deadLine;
    }
}
