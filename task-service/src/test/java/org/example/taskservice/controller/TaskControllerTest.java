package org.example.taskservice.controller;

import org.example.taskservice.model.Task;
import org.example.taskservice.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {
    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskController taskController;

    public TaskControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test");
        when(taskService.createTask(any(Task.class))).thenReturn(task);
        ResponseEntity<Task> response = taskController.createTask(task);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test", response.getBody().getTitle());
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task();
        task.setTitle("Test");
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task));
        ResponseEntity<java.util.List<Task>> response = taskController.getAllTasks();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetTaskByIdFound() {
        Task task = new Task();
        task.setId(1L);
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));
        ResponseEntity<Task> response = taskController.getTaskById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetTaskByIdNotFound() {
        when(taskService.getTaskById(2L)).thenReturn(Optional.empty());
        ResponseEntity<Task> response = taskController.getTaskById(2L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testUpdateTaskFound() {
        Task updated = new Task();
        updated.setTitle("Updated");
        when(taskService.updateTask(1L, updated)).thenReturn(updated);
        ResponseEntity<Task> response = taskController.updateTask(1L, updated);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody().getTitle());
    }

    @Test
    void testUpdateTaskNotFound() {
        Task updated = new Task();
        when(taskService.updateTask(2L, updated)).thenReturn(null);
        ResponseEntity<Task> response = taskController.updateTask(2L, updated);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteTaskFound() {
        when(taskService.deleteTask(1L)).thenReturn(true);
        ResponseEntity<Void> response = taskController.deleteTask(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteTaskNotFound() {
        when(taskService.deleteTask(2L)).thenReturn(false);
        ResponseEntity<Void> response = taskController.deleteTask(2L);
        assertEquals(404, response.getStatusCodeValue());
    }
}
