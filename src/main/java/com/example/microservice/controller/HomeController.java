package com.example.microservice.controller;

import com.example.microservice.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }

    /** Renders the home page. */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /** Handles form submission and displays the response. */
    @PostMapping("/submit")
    public String submit(@RequestParam("userInput") String userInput, Model model) {
        String response = messageService.processMessage(userInput);
        model.addAttribute("response", response);
        return "home";
    }
}
