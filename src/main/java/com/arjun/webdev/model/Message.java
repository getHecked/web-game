package com.arjun.webdev.model;

import lombok.Data;

import java.util.Map;

@Data
public class Message {
    private String type;
    private Map<String, Object> features;
}
