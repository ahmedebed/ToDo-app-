package com.example.todoapp.Service;

import com.example.todoapp.Entity.Task;
import com.example.todoapp.Reporastiry.TaskRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
 public class TaskService {
    private final TaskRepo taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    //@Transactional
    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setComplete(false);
        taskRepo.save(task);
    }

    //@Transactional
    public void deleteTask(Long id) {
        if (!taskRepo.existsById(id)) {
            throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
        }
        taskRepo.deleteById(id);
    }

   // @Transactional
    public void toggleTaskCompletion(Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " does not exist."));
        task.setComplete(!task.getComplete());
        taskRepo.save(task);
    }
}