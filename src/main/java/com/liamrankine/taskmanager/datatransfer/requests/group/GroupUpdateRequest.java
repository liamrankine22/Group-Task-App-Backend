package com.liamrankine.taskmanager.datatransfer.requests.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class GroupUpdateRequest {
    @NotNull
    private Long id;
    private String name;
    private String description;

    //Constructors
    public GroupUpdateRequest() {}
    public GroupUpdateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }

    //Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
