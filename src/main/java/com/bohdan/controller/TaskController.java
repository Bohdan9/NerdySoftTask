package com.bohdan.controller;

import com.bohdan.model.Task;
import com.bohdan.model.User;
import com.bohdan.service.serviceImpl.TaskServiceImpl;
import com.bohdan.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    GlobalController globalController;

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String showAll(Model model, final RedirectAttributes redirectAttributes) {
        List<Task> allies = taskService.findAllTaskById(globalController.getLoginUser().getId());
        if (allies.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "You don't have tasks");
        }
        model.addAttribute("allies", allies);
        return "index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Task user = taskService.findById(id);
        model.addAttribute("user", user);
        return "update-task";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, @Valid Task task,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            task.setId(id);
            return "update-task";
        }
        task.setUserId(globalController.getLoginUser().getId());
        taskService.addTask(task);
        model.addAttribute("allies", taskService.findAllTaskById(id));
        return "index";
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public String getTask(
            @PathVariable int id,
            Model model
    ) {
        Optional<Task> taskOptional = Optional.ofNullable(taskService.findById(id));
        Task allies = taskOptional.get();
        model.addAttribute("allies", allies);
        return "task";
    }

    @RequestMapping(value = "/task/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(
            @PathVariable int id
    ) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/showShareTask", method = RequestMethod.GET)
    public String showShareTask() {
        return "share";
    }

    @RequestMapping(value = "/shareTask", method = RequestMethod.POST)
    public String shareTask(User user, Task task, Model model) {
        User getMail = userService.findByEmail(user.getEmail());
        task.setUserId(getMail.getId());
        task.setSendFrom(globalController.getLoginUser().getEmail());
        Task getDescribe = taskService.findByDescribeTask(task.getDescribeTask());
        task.setDescribeTask(getDescribe.getDescribeTask());
        Task getText = taskService.findByTextForTask(task.getTextForTask());
        task.setTextForTask(getText.getTextForTask());
        taskService.addTask(task);
        return "redirect:/";
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.GET)
    public String showAddTask() {
        return "addTask";
    }

    @RequestMapping(value = "/newTaskCreate", method = RequestMethod.POST)
    public String addTask(Task task) {
        task.setUserId(globalController.getLoginUser().getId());
        taskService.addTask(task);
        return "redirect:/";
    }
}








