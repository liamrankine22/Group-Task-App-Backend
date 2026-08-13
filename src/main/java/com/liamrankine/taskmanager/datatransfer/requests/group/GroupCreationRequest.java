package com.liamrankine.taskmanager.datatransfer.requests.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GroupCreationRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Long creatorId;

    //Constructors
    public GroupCreationRequest() {}
    public GroupCreationRequest(String name, String description, Long creatorId) {
        this.name = name;
        this.creatorId = creatorId;
    }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCreatorId(Long creatorId) { this. creatorId = creatorId; }

    //Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Long getCreatorId() { return creatorId; }
}
