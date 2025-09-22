package org.example.notificationservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class NotificationControllerTest {
    private final NotificationController controller = new NotificationController();

    @Test
    void testNotify() {
        ResponseEntity<String> response = controller.notify("Test notification");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Notification logged", response.getBody());
    }
}
