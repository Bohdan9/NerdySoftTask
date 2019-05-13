package com.bohdan.controller;

import com.bohdan.Application;
import com.bohdan.helper.Constant;
import com.bohdan.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskController {

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        int hostPort = 8080;
        return "http://localhost:" + hostPort;
    }

    @Test
    public void testGetAllTask() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/showAll",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println(response);
    }

    @Test
    public void testCreateTask() {
        Constant constant = new Constant();
        Task task = new Task();
        task.setId(constant.SET_ID_FOR_TASK);
        task.setDescribeTask(constant.SET_DESCRIBE_FOR_TASK);
        task.setTextForTask(constant.SET_TEXT_FOR_TASK);
        ResponseEntity<Task> postResponse = restTemplate.postForEntity(getRootUrl() + "/newTaskCreate", task, Task.class);
        assertNotNull(postResponse);
        System.out.println(task);
    }

    @Test
    public void testUpdateEmployee() {
        Constant constant = new Constant();
        int id = 1;
        Task task = restTemplate.getForObject(getRootUrl() + "/update/" + id, Task.class);
        task.setDescribeTask(constant.SET_DESCRIBE_FOR_TASK);
        task.setTextForTask(constant.SET_TEXT_FOR_TASK);
        restTemplate.postForLocation(getRootUrl() + "/update/" + id, task);
        ResponseEntity updatedTask = restTemplate.postForEntity(getRootUrl() + "/update/"+ id, task, Task.class);
        assertNotNull(updatedTask);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Task task = restTemplate.getForObject(getRootUrl() + "/task/delete/" + id, Task.class);
        assertNotNull(task);
        restTemplate.delete(getRootUrl() + "/task/delete/" + id);
        try {
            task = restTemplate.getForObject(getRootUrl() + "/task/delete/" + id, Task.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
        System.out.println(task);
    }
}
