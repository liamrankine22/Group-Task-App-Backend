package com.liamrankine.taskmanager.datatransfer.responses;

import com.liamrankine.taskmanager.entities.TaskAssignment;
import java.time.LocalDate;

public class TaskAssignmentResponse {

    private Long id;
    private Long assignedUserId;
    private String assignedUsername;
    private Long taskId;
    private LocalDate assignedAt;

    // Constructors
    public TaskAssignmentResponse() {}

    public TaskAssignmentResponse(Long id, Long assignedUserId, String assignedUsername, Long taskId, LocalDate assignedAt) {
        this.id = id;
        this.assignedUserId = assignedUserId;
        this.assignedUsername = assignedUsername;
        this.taskId = taskId;
        this.assignedAt = assignedAt;
    }

    public TaskAssignmentResponse(TaskAssignment taskAssignment) {
        convertTaskAssignment(taskAssignment);
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public void setAssignedUsername(String assignedUsername) {
        this.assignedUsername = assignedUsername;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setAssignedAt(LocalDate assignedAt) {
        this.assignedAt = assignedAt;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public String getAssignedUsername() {
        return assignedUsername;
    }

    public Long getTaskId() {
        return taskId;
    }

    public LocalDate getAssignedAt() {
        return assignedAt;
    }

    // Helper
    private void convertTaskAssignment(TaskAssignment taskAssignment) {
        this.id = taskAssignment.getId();
        this.assignedUserId = taskAssignment.getAssignedUser().getId();
        this.assignedUsername = taskAssignment.getAssignedUser().getUsername();
        this.taskId = taskAssignment.getTask().getId();
        this.assignedAt = taskAssignment.getAssignedAt();
    }
}
