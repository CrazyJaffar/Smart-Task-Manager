package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String notificationUrl = "http://localhost:8083/notifications";

    public Task createTask(Task task) {
        Task created = taskRepository.save(task);
        restTemplate.postForEntity(notificationUrl, "Task created: " + created.getTitle(), String.class);
        return created;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            Task saved = taskRepository.save(task);
            restTemplate.postForEntity(notificationUrl, "Task updated: " + saved.getTitle(), String.class);
            return saved;
        }).orElse(null);
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
