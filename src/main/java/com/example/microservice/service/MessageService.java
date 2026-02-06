package com.example.microservice.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    /**
     * Processes the user input and returns a response message.
     */
    public String processMessage(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Please enter a valid message.";
        }
        return "Hello! You submitted: " + input.trim();
    }
}
