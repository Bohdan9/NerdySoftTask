package com.bohdan.service.serviceImpl;

import com.bohdan.model.Task;
import com.bohdan.repository.TaskRepository;
import com.bohdan.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public Task findById(int id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTaskById(int id) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task findBySendFrom(String sendFrom) {
        return taskRepository.findBySendFrom(sendFrom);
    }

    @Override
    public Task findByDescribeTask(String describe) {
        return (taskRepository.findByDescribeTask(describe));
    }

    @Override
    public Task findByTextForTask(String text) {
        return taskRepository.findByTextForTask(text);
    }


}
