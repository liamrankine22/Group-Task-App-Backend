package com.liamrankine.taskmanager.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupMembership> memberships = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    //Constructors
    public Group() {}
    public Group(String name) {
        this.name = name;
    }

    //Helpers
    public void addMembership(GroupMembership membership) {
        memberships.add(membership);
    }

    public void removeMembership(GroupMembership membership) {
        memberships.remove(membership);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.add(task);
    }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setMemberships(Set<GroupMembership> memberships) { this.memberships = memberships; }
    public void setTasks(Set<Task> tasks) { this.tasks = tasks; }

    //Getters
    public Long getId() { return id;}
    public String getName() { return name; }
    public Set<GroupMembership> getMemberships() { return memberships; }
    public Set<Task> getTasks() { return tasks; }
}
