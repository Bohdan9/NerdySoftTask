package com.bohdan.model;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String describeTask;
    private String textForTask;
    private int userId;
    private String sendFrom;

    public Task() {
    }

    public Task(String describeTask, String textForTask, int userId, String sendFrom) {
        this.describeTask = describeTask;
        this.textForTask = textForTask;
        this.userId = userId;
        this.sendFrom = sendFrom;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribeTask() {
        return describeTask;
    }

    public void setDescribeTask(String describeTask) {
        this.describeTask = describeTask;
    }

    public String getTextForTask() {
        return textForTask;
    }

    public void setTextForTask(String textForTask) {
        this.textForTask = textForTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", describeTask='" + describeTask + '\'' +
                ", textForTask='" + textForTask + '\'' +
                ", userId=" + userId +
                '}';
    }
}
