package com.arjun.webdev.controller;

import com.arjun.webdev.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class DrawingController {

    private final Map<String, List<Message>> messagesByType = Collections.synchronizedMap(new HashMap<>());

    @MessageMapping("/draw")
    @SendTo("/topic/drawings")
    public Message drawing(Message message) {
        messagesByType.putIfAbsent(message.getType(), Collections.synchronizedList(new ArrayList<>()));
        messagesByType.get(message.getType()).add(message);
        return message;
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getMessages(@RequestParam("types") List<String> types) {
        List<Message> result = new ArrayList<>();
        for (String type : types) {
            List<Message> messages = messagesByType.get(type);
            if (messages != null) {
                result.addAll(messagesByType.get(type));
            }
        }
        return result;
    }

}
