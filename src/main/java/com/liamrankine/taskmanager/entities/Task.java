package com.liamrankine.taskmanager.entities;

import com.liamrankine.taskmanager.datatransfer.requests.task.TaskUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    private String description;
    @NotNull
    private String status;
    private LocalDate dueDate;
    private LocalDate createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by_id", nullable = false)
    private AppUser createdBy;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskAssignment> assignments = new HashSet<>();


    //Constructors
    public Task() {}
    public Task(String title, String description, String status, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.assignments = new HashSet<>();
        this.createdDate = LocalDate.now();
    }

    //Helpers
    public void addTaskAssignment(TaskAssignment assignment) {
        assignments.add(assignment);
    }

    public void removeTaskAssignment(TaskAssignment assignment) {
        assignments.remove(assignment);
    }

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setGroup(Group group) { this.group = group; }
    public void setAssignments(Set<TaskAssignment> assignments) { this.assignments = assignments; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }
    public void setCreatedBy(AppUser createdBy) { this.createdBy = createdBy; }

    //Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }
    public Group getGroup() { return group; }
    public Set<TaskAssignment> getAssignments() { return assignments; }
    public LocalDate getCreatedDate() { return createdDate; }
    public AppUser getCreatedBy() { return createdBy; }

    //Helpers
    public void updateByRequest(TaskUpdateRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.status = request.getStatus();
        this.dueDate = request.getDueDate();
    }
}