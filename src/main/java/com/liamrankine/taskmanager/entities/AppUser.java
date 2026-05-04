package com.liamrankine.taskmanager.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupMembership> memberships = new HashSet<>();

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskAssignment> assignedTasks = new HashSet<>();

    //Constructors
    public AppUser() {}
    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.memberships = new HashSet<>();
        this.assignedTasks = new HashSet<>();
    }

    //Helpers
    public void addMembership(GroupMembership membership) {
        memberships.add(membership);
    }

    public void removeMembership(GroupMembership membership) {
        memberships.remove(membership);
    }

    public void addAssignment(TaskAssignment assignment) {
        assignedTasks.add(assignment);
    }

    public void removeAssignment(TaskAssignment assignment) {
        assignedTasks.remove(assignment);
    }

    //Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setMemberships(Set<GroupMembership> memberships) { this.memberships = memberships; }
    public void setAssignedTasks(Set<TaskAssignment> assignedTasks) { this.assignedTasks = assignedTasks; }

    //Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public Set<GroupMembership> getMemberships() { return memberships; }
    public Set<TaskAssignment> getAssignedTasks() { return assignedTasks; }
}
