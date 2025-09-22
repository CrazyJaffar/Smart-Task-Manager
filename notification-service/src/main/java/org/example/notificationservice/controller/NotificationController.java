package org.example.notificationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @PostMapping
    public ResponseEntity<String> notify(@RequestBody String message) {
        System.out.println("Notification received: " + message);
        return ResponseEntity.ok("Notification logged");
    }
}

