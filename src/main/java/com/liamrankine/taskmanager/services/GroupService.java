package com.liamrankine.taskmanager.services;

import com.liamrankine.taskmanager.datatransfer.requests.group.GroupCreationRequest;
import com.liamrankine.taskmanager.datatransfer.requests.group.GroupUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.requests.groupmembership.GroupMembershipCreateRequest;
import com.liamrankine.taskmanager.datatransfer.requests.groupmembership.GroupMembershipUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.responses.GroupResponse;
import com.liamrankine.taskmanager.entities.*;
import com.liamrankine.taskmanager.enumerations.GroupRole;
import com.liamrankine.taskmanager.enumerations.UpdateType;
import com.liamrankine.taskmanager.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final AppUserRepository appUserRepo;
    private final GroupRepository groupRepo;
    private final TaskRepository taskRepo;
    private final GroupMembershipRepository groupMembershipRepo;
    private final TaskAssignmentRepository taskAssignmentRepo;
    private final PasswordEncoder passwordEncoder;

    public GroupService(AppUserRepository appUserRepo, GroupRepository groupRepo,
                          TaskRepository taskRepo, PasswordEncoder passwordEncoder,
                          GroupMembershipRepository groupMembershipRepo,
                          TaskAssignmentRepository taskAssignmentRepo
    ) {
        this.appUserRepo = appUserRepo;
        this.groupRepo = groupRepo;
        this.taskRepo = taskRepo;
        this.groupMembershipRepo = groupMembershipRepo;
        this.taskAssignmentRepo = taskAssignmentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //GETS
    public List<GroupResponse> getGroups() {
        List<Group> groups = groupRepo.findAll();
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            GroupResponse newResponse = new GroupResponse(group);
            responses.add(newResponse);
        }
        return responses;
    }

    public GroupResponse getGroupByID(Long id) {
        Group group = groupRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group with id: " + id));
        return new GroupResponse(group);
    }

    public List<GroupResponse> getGroupByUserID(Long id) {
        appUserRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<GroupMembership> groupMemberships = groupMembershipRepo.findByMember_Id(id);

        return groupMemberships.stream().map(GroupMembership::getGroup).map(GroupResponse::new).toList();
    }

    //POSTS
    @Transactional
    public void createGroup(GroupCreationRequest request) {

        Group group = new Group(request.getName(), request.getDescription());
        AppUser owner = appUserRepo.findById(request.getCreatorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));

        GroupMembership membership = new GroupMembership(group, owner, GroupRole.OWNER);
        group.addMembership(membership);

        groupRepo.save(group);
        groupMembershipRepo.save(membership);
    }

    @Transactional
    public void createGroupMembership(GroupMembershipCreateRequest request) {
        Group group = groupRepo.findById(request.getGroupId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group"));
        AppUser user = appUserRepo.findById(request.getMemberId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
        GroupRole role = request.getRole();

        GroupMembership membership = new GroupMembership(group, user, role);
        group.addMembership(membership);
        user.addMembership(membership);

        groupMembershipRepo.save(membership);
    }

    //PATCHES
    @Transactional
    public void updateGroup(Long id, GroupUpdateRequest request) {

        Group group = groupRepo.findById(request.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group"));
        group.setName(request.getName());
    }

    @Transactional
    public void updateGroupMembership(GroupMembershipUpdateRequest request) {
        Group group = groupRepo.findById(request.getGroupId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group"));
        AppUser user = appUserRepo.findById(request.getMemberId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
        GroupMembership membership = groupMembershipRepo.findByGroup_IdAndMember_Id(group.getId(), user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find membership"));

        membership.setRole(request.getRole());
    }

    //DELETES
    @Transactional
    public void deleteGroupByID(Long id) {
        Group deletedGroup = groupRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group"));
        groupRepo.delete(deletedGroup);
    }

    @Transactional
    public void deleteGroupMembershipByIDs(Long groupId, Long memberId) {
        GroupMembership membership = groupMembershipRepo.findByGroup_IdAndMember_Id(groupId, memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group or user"));
        membership.getGroup().removeMembership(membership);
    }
}
