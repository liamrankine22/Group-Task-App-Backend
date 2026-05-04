package com.liamrankine.taskmanager.controllers;

import com.liamrankine.taskmanager.datatransfer.requests.task.TaskCreationRequest;
import com.liamrankine.taskmanager.datatransfer.requests.task.TaskUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.requests.taskassignment.TaskAssignmentCreationRequest;
import com.liamrankine.taskmanager.datatransfer.responses.TaskResponse;
import com.liamrankine.taskmanager.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskByID(@PathVariable Long id) {
        return taskService.getTaskByID(id);
    }

    @GetMapping("/group/{groupId}")
    public List<TaskResponse> getTasksByGroupId(@PathVariable Long groupId) {
        return taskService.getTasksByGroupId(groupId);
    }

    @PostMapping("/create")
    public void createTask(@RequestBody TaskCreationRequest request) {
        taskService.createTask(request);
    }

    @PostMapping("/assignment/create")
    public void createTaskAssignment(@RequestBody TaskAssignmentCreationRequest request) {
        taskService.createTaskAssignment(request);
    }

    @PatchMapping("/update")
    public void updateTask(@RequestBody TaskUpdateRequest request) {
        taskService.updateTask(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping("/assignment/delete/{taskId}/{userId}")
    public void deleteTaskAssignment(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.deleteTaskAssignment(taskId, userId);
    }
}