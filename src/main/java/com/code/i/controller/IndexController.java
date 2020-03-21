package com.code.i.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    private final Map<String, String> STATUS = Map.of("status", "up");

    @GetMapping
    Map<String, String> get() {
        return STATUS;
    }

    @GetMapping("favicon.ico")
    String favicon() {
        return "";
    }


}
