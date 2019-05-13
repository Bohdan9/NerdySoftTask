package com.bohdan.controller;

import com.bohdan.Application;
import com.bohdan.helper.Constant;
import com.bohdan.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        int hostPort = 8080;
        return "http://localhost:" + hostPort;
    }

    @Test
    public  void testCreateTask() {
        User user = new User();
        Constant constant = new Constant();
        user.setId(constant.SET_ID_FOR_USER);
        user.setEmail(constant.SET_EMAIL_FOR_USER);
        user.setFirstName(constant.SET_FIRST_NAME_FOR_USER);
        user.setLastName(constant.SET_LAST_NAME_FOR_USER);
        user.setPassword(constant.SET_PASSWORD_FOR_USER);
        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/registration", user, User.class);
        assertNotNull(postResponse);
        System.out.println(user);
    }
}


