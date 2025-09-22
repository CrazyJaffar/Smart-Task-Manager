package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private TaskService taskService;

    public TaskServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test");
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(null);
        Task result = taskService.createTask(task);
        assertEquals("Test", result.getTitle());
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task();
        task.setTitle("Test");
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
        List<Task> result = taskService.getAllTasks();
        assertEquals(1, result.size());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> result = taskService.getTaskById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Old");
        Task updated = new Task();
        updated.setTitle("New");
        updated.setDescription("Desc");
        updated.setCompleted(true);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updated);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(null);
        Task result = taskService.updateTask(1L, updated);
        assertEquals("New", result.getTitle());
        assertTrue(result.isCompleted());
    }

    @Test
    void testDeleteTask() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1L);
        boolean result = taskService.deleteTask(1L);
        assertTrue(result);
    }
}
