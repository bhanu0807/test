package com.example.microservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageService = new MessageService();
    }

    @Test
    void processMessage_withValidInput_returnsGreeting() {
        String result = messageService.processMessage("World");
        assertEquals("Hello! You submitted: World", result);
    }

    @Test
    void processMessage_withWhitespace_trimInput() {
        String result = messageService.processMessage("  Hello  ");
        assertEquals("Hello! You submitted: Hello", result);
    }

    @Test
    void processMessage_withNull_returnsErrorMessage() {
        String result = messageService.processMessage(null);
        assertEquals("Please enter a valid message.", result);
    }

    @Test
    void processMessage_withEmptyString_returnsErrorMessage() {
        String result = messageService.processMessage("");
        assertEquals("Please enter a valid message.", result);
    }

    @Test
    void processMessage_withBlankString_returnsErrorMessage() {
        String result = messageService.processMessage("   ");
        assertEquals("Please enter a valid message.", result);
    }
}
