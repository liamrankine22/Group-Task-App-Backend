package com.liamrankine.taskmanager.datatransfer.requests.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GroupCreationRequest {
    @NotNull
    private String name;
    @NotNull
    private Long creatorId;

    //Constructors
    public GroupCreationRequest() {}
    public GroupCreationRequest(String name, Long creatorId) {
        this.name = name;
        this.creatorId = creatorId;
    }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setCreatorId(Long creatorId) { this. creatorId = creatorId; }

    //Getters
    public String getName() { return name; }
    public Long getCreatorId() { return creatorId; }
}
