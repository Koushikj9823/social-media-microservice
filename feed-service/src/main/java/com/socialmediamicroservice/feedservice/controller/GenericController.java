package com.socialmediamicroservice.feedservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {
    private final String applicationName;
    public GenericController(@Value("${spring.application.name}") String applicationName) {
        this.applicationName = applicationName;
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Hello Anonymous!");
    }
    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok(" This is auto-generated " + applicationName + " application");
    }
}
