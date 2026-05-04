package com.liamrankine.taskmanager.datatransfer.requests.groupmembership;

import com.liamrankine.taskmanager.enumerations.GroupRole;

public class GroupMembershipCreateRequest {
    private Long groupId;
    private Long memberId;
    private GroupRole role;

    //Constructors
    public GroupMembershipCreateRequest() {}
    public GroupMembershipCreateRequest(Long groupId, Long memberId, GroupRole role) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.role = role;
    }

    //Setters
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public void setRole(GroupRole role) {
        this.role = role;
    }

    //Getters
    public Long getGroupId() {
        return groupId;
    }
    public Long getMemberId() {
        return memberId;
    }
    public GroupRole getRole() {
        return role;
    }
}
