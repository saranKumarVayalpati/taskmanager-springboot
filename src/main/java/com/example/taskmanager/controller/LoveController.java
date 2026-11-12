package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoveController {

    @GetMapping("/love")
    public String sayLove() {
        return "I love you Potti ❤️ Forever and Always! 🥰";
    }
}
