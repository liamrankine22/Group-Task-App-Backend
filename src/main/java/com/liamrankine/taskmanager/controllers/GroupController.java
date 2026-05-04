package com.liamrankine.taskmanager.controllers;

import com.liamrankine.taskmanager.datatransfer.requests.group.GroupCreationRequest;
import com.liamrankine.taskmanager.datatransfer.requests.group.GroupUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.requests.groupmembership.GroupMembershipCreateRequest;
import com.liamrankine.taskmanager.datatransfer.requests.groupmembership.GroupMembershipUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.responses.GroupResponse;
import com.liamrankine.taskmanager.services.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupResponse> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public GroupResponse getGroupByID(@PathVariable Long id) {
        return groupService.getGroupByID(id);
    }

    @GetMapping("/user/{id}")
    public List<GroupResponse> getGroupByUserID(@PathVariable Long id) { return groupService.getGroupByUserID(id); }

    @PostMapping("/register")
    public void registerGroup(@RequestBody GroupCreationRequest request) {
        groupService.createGroup(request);
    }

    @PostMapping("/membership/create")
    public void createGroupMembership(@RequestBody GroupMembershipCreateRequest request) {
        groupService.createGroupMembership(request);
    }

    @PatchMapping("/update/{id}")
    public void updateGroup(@PathVariable Long id, @RequestBody GroupUpdateRequest request) {
        groupService.updateGroup(id, request);
    }

    @PatchMapping("/membership/update")
    public void updateGroupMembership(@RequestBody GroupMembershipUpdateRequest request) {
        groupService.updateGroupMembership(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroupByID(@PathVariable Long id) {
        groupService.deleteGroupByID(id);
    }

    @DeleteMapping("/membership/delete/{groupId}/{memberId}")
    public void deleteGroupMembershipByIDs(@PathVariable Long groupId, @PathVariable Long memberId) {
        groupService.deleteGroupMembershipByIDs(groupId, memberId);
    }
}
