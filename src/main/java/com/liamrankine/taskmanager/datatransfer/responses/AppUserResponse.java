package com.liamrankine.taskmanager.datatransfer.responses;

import com.liamrankine.taskmanager.entities.AppUser;
import com.liamrankine.taskmanager.entities.GroupMembership;
import com.liamrankine.taskmanager.entities.TaskAssignment;

import java.util.ArrayList;
import java.util.List;

public class AppUserResponse {

    private Long id;
    private String username;
    private String email;
    private List<GroupMembershipResponse> membershipResponses = new ArrayList<>();
    private List<TaskAssignmentResponse> taskAssignmentResponses = new ArrayList<>();

    // Constructors
    public AppUserResponse() {}

    public AppUserResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public AppUserResponse(AppUser user) {
        convertAppUser(user);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<GroupMembershipResponse> getMembershipResponses() {
        return membershipResponses;
    }

    public List<TaskAssignmentResponse> getTaskAssignmentResponses() {
        return taskAssignmentResponses;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMembershipResponses(List<GroupMembershipResponse> membershipResponses) {
        this.membershipResponses = membershipResponses;
    }

    public void setTaskAssignmentResponses(List<TaskAssignmentResponse> taskAssignmentResponses) {
        this.taskAssignmentResponses = taskAssignmentResponses;
    }

    // Helpers
    private void convertAppUser(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();

        for (GroupMembership membership : user.getMemberships()) {
            GroupMembershipResponse response = new GroupMembershipResponse(membership);
            this.membershipResponses.add(response);
        }

        for (TaskAssignment assignment : user.getAssignedTasks()) {
            TaskAssignmentResponse response = new TaskAssignmentResponse(assignment);
            this.taskAssignmentResponses.add(response);
        }
    }
}
