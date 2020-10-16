package com.anrymart.webdev.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DrawingController {

    @MessageMapping("/draw")
    @SendTo("/topic/drawings")
    public String drawing(String message) {
        return message;
    }
}
