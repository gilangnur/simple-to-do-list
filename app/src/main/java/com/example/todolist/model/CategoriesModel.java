package com.example.todolist.model;

import com.example.todolist.adapter.CategoriesViewAdapter;

import java.util.ArrayList;

public class CategoriesModel {
    private String categoryName;
    private ArrayList<TaskModel> tasks;

    public CategoriesModel(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryName() {
        this.categoryName = categoryName;
    }

    public void setTasks(ArrayList<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public ArrayList<TaskModel> getTasks() {
        return this.tasks;
    }
}
