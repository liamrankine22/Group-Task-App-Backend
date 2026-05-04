package com.liamrankine.taskmanager.datatransfer.responses;

import com.liamrankine.taskmanager.entities.Group;
import com.liamrankine.taskmanager.entities.GroupMembership;
import com.liamrankine.taskmanager.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class GroupResponse {

    private Long id;
    private String name;
    private List<GroupMembershipResponse> membershipResponses = new ArrayList<>();
    private List<TaskResponse> taskResponses = new ArrayList<>();

    // Constructors
    public GroupResponse() {}

    public GroupResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupResponse(Group group) {
        convertGroup(group);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroupMembershipResponse> getMembershipResponses() {
        return membershipResponses;
    }

    public List<TaskResponse> getTaskResponses() {
        return taskResponses;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipResponses(List<GroupMembershipResponse> membershipResponses) {
        this.membershipResponses = membershipResponses;
    }

    public void setTaskResponses(List<TaskResponse> taskResponses) {
        this.taskResponses = taskResponses;
    }

    // Helper method
    private void convertGroup(Group group) {
        this.id = group.getId();
        this.name = group.getName();

        if (group.getMemberships() != null) {
            for (GroupMembership membership : group.getMemberships()) {
                this.membershipResponses.add(new GroupMembershipResponse(membership));
            }
        }

        if (group.getTasks() != null) {
            for (Task task : group.getTasks()) {
                this.taskResponses.add(new TaskResponse(task));
            }
        }
    }
}
