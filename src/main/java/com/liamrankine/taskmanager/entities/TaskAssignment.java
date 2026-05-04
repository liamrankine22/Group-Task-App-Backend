package com.liamrankine.taskmanager.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TaskAssignment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser assignedUser;

    @ManyToOne(optional = false)
    @JoinColumn(name="task_id", nullable = false)
    private Task task;

    private LocalDate assignedAt;

    // Constructors
    public TaskAssignment() {}
    public TaskAssignment(AppUser assignedUser, Task task, LocalDate assignedAt) {
        this.assignedUser = assignedUser;
        this.task = task;
        this.assignedAt = assignedAt;
    }

    // Setters
    public void setAssignedUser(AppUser assignedUser) { this.assignedUser = assignedUser; }
    public void setTask(Task task) { this.task = task; }
    public void setAssignedAt(LocalDate assignedAt) { this.assignedAt = assignedAt; }

    // Getters
    public Long getId() { return id; }
    public AppUser getAssignedUser() { return assignedUser; }
    public Task getTask() { return task; }
    public LocalDate getAssignedAt() { return assignedAt; }

}
