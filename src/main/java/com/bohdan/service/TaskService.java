package com.bohdan.service;

import com.bohdan.model.Task;

import java.util.List;

public interface TaskService {

    Task findById(int id);

    Task update(Task task);

    void addTask(Task task);

    List<Task> findAllTaskById(int id);

    List<Task> findAll();

    void deleteTask(int id);

    Task findBySendFrom(String sendFrom);

    Task findByDescribeTask(String describe);

    Task findByTextForTask(String text);
}
