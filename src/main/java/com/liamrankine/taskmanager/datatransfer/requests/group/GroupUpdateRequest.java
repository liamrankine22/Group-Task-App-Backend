package com.liamrankine.taskmanager.datatransfer.requests.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class GroupUpdateRequest {
    @NotNull
    private Long id;
    private String name;

    //Constructors
    public GroupUpdateRequest() {}
    public GroupUpdateRequest(String name) {
        this.name = name;
    }

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    //Getters
    public Long getId() { return id; }
    public String getName() { return name; }
}
