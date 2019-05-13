package com.bohdan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bohdan.model.User;
import com.bohdan.service.UserService;
import com.bohdan.config.UserRegistrationConfiguration;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationConfiguration userRegistrationDto() {
        return new UserRegistrationConfiguration();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(  @Valid UserRegistrationConfiguration userDto,
                                      BindingResult result){
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()){
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
}
