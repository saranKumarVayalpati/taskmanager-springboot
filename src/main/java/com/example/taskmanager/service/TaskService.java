package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public TaskService() {
        create(new Task(null, "Explore Spring Boot", "Build and run the first API", TaskStatus.TODO));
    }

    public List<Task> findAll() {
        return tasks.values().stream().sorted(Comparator.comparing(Task::getId)).toList();
    }

    public Task findById(Long id) {
        Task task = tasks.get(id);
        if (task == null) throw new TaskNotFoundException(id);
        return task;
    }

    public Task create(Task task) {
        long id = sequence.incrementAndGet();
        task.setId(id);
        if (task.getStatus() == null) task.setStatus(TaskStatus.TODO);
        tasks.put(id, task);
        return task;
    }

    public Task update(Long id, Task input) {
        if (!tasks.containsKey(id)) throw new TaskNotFoundException(id);
        input.setId(id);
        if (input.getStatus() == null) input.setStatus(TaskStatus.TODO);
        tasks.put(id, input);
        return input;
    }

    public void delete(Long id) {
        if (tasks.remove(id) == null) throw new TaskNotFoundException(id);
    }
}
