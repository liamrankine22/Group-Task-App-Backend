package com.liamrankine.taskmanager.datatransfer.requests.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskCreationRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String status;
    @NotBlank
    private LocalDate dueDate;
    @NotNull
    private Long groupId;
    @NotNull
    private Long creatorId;

    //Constructors
    public TaskCreationRequest() {}
    public TaskCreationRequest(String title, String description, String status, LocalDate dueDate, Long groupId, Long creatorId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.groupId = groupId;
        this.creatorId = creatorId;
    }

    //Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }

    //Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }
    public Long getGroupId() { return groupId; }
    public Long getCreatorId() { return creatorId; }
}
