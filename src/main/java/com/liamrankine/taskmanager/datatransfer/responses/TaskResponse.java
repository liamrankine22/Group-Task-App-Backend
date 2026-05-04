package com.liamrankine.taskmanager.datatransfer.responses;

import com.liamrankine.taskmanager.entities.Group;
import com.liamrankine.taskmanager.entities.Task;
import com.liamrankine.taskmanager.entities.TaskAssignment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private Long createdById;
    private GroupSummaryResponse groupSummary = new GroupSummaryResponse();
    private List<TaskAssignmentResponse> assignmentSummary = new ArrayList<>();

    // Constructors
    public TaskResponse() {}

    public TaskResponse(Long id, String title, String description, String status, LocalDate dueDate,
                        LocalDate createdDate, Long createdById, Group group) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.createdById = createdById;
        this.groupSummary.convertFromGroup(group);

    }

    public TaskResponse(Task task) {
        convertTask(task);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public GroupSummaryResponse getGroupSummary() {
        return groupSummary;
    }

    public List<TaskAssignmentResponse> getAssignmentSummary() {
        return assignmentSummary;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public void setGroupSummary(GroupSummaryResponse groupSummary) {
        this.groupSummary = groupSummary;
    }

    public void setAssignmentSummary(List<TaskAssignmentResponse> assignmentSummary) {
        this.assignmentSummary = assignmentSummary;
    }

    // Helper method to convert entity to DTO
    private void convertTask(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.dueDate = task.getDueDate();
        this.createdDate = task.getCreatedDate();

        if (task.getCreatedBy() != null) {
            this.createdById = task.getCreatedBy().getId();
        }

        if (task.getGroup() != null) {
            this.groupSummary.convertFromGroup(task.getGroup());
        }

        for (TaskAssignment assignment : task.getAssignments()) {
            TaskAssignmentResponse response = new TaskAssignmentResponse(assignment);
            this.assignmentSummary.add(response);
        }
    }
}
