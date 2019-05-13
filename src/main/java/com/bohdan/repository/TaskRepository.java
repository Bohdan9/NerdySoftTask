package com.bohdan.repository;

import com.bohdan.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByUserId(int id);

    Task findByDescribeTask(String describe);

    Task findByTextForTask(String text);

    Task findBySendFrom(String text);
}
