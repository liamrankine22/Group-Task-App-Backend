package com.liamrankine.taskmanager.datatransfer.responses;

import com.liamrankine.taskmanager.entities.Group;
import com.liamrankine.taskmanager.entities.GroupMembership;
import com.liamrankine.taskmanager.enumerations.GroupRole;

public class GroupMembershipResponse {

    private Long id;
    private Long userId;
    private String username;
    private GroupSummaryResponse groupSummary = new GroupSummaryResponse();
    private GroupRole role;

    //Constructors
    public GroupMembershipResponse(){}
    public GroupMembershipResponse(Long id, Long userId, String username, Group group, Long memberId, GroupRole role) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.groupSummary.convertFromGroup(group);
        this.role = role;
    }
    public GroupMembershipResponse(GroupMembership membership) {
        convertGroupMembership(membership);
    }

    //Getters
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setGroupSummary(GroupSummaryResponse groupSummary) {
        this.groupSummary = groupSummary;
    }
    public void setRole(GroupRole role) {
        this.role = role;
    }

    //Getters
    public Long getId() {
        return id;
    }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public GroupSummaryResponse getGroupSummary() {
        return groupSummary;
    }
    public GroupRole getRole() {
        return role;
    }

    //Helpers
    private void convertGroupMembership(GroupMembership groupMembership) {
        this.id = groupMembership.getId();
        this.userId = groupMembership.getMember().getId();
        this.username = groupMembership.getMember().getUsername();
        this.groupSummary.convertFromGroup(groupMembership.getGroup());
        this.role = groupMembership.getRole();
    }
}
