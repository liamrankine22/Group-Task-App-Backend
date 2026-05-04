package com.liamrankine.taskmanager.datatransfer.responses;

import com.liamrankine.taskmanager.entities.Group;

public class GroupSummaryResponse {
    private Long id;
    private String name;

    public GroupSummaryResponse() {}

    public GroupSummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Helpers
    public void convertFromGroup(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }
}
