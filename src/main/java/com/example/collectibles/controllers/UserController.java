package com.example.collectibles.controllers;

import com.example.collectibles.dao.UserRepository;
import com.example.collectibles.model.User;
import com.example.collectibles.validators.UserValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

//    private UserValidator userValidator;
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
//        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

//    @InitBinder
//    public void bindUser(WebDataBinder binder) {
//        binder.addValidators(this.userValidator);
//    }

    @GetMapping("/newUser")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        // validate and save to db
        if (result.hasErrors()) {
            return "register-user";
        }

        User savedUser = userRepository.save(user);
        if (savedUser != null) {
            model.addAttribute("userSaved", savedUser);
        }
        return "register-user";
    }
}
