package com.liamrankine.taskmanager.services;

import com.liamrankine.taskmanager.datatransfer.requests.appuser.AppUserRegistrationRequest;
import com.liamrankine.taskmanager.datatransfer.requests.appuser.AppUserUpdateRequest;
import com.liamrankine.taskmanager.datatransfer.responses.AppUserResponse;
import com.liamrankine.taskmanager.entities.*;
import com.liamrankine.taskmanager.enumerations.GroupRole;
import com.liamrankine.taskmanager.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepo;
    private final GroupRepository groupRepo;
    private final TaskRepository taskRepo;
    private final GroupMembershipRepository groupMembershipRepo;
    private final TaskAssignmentRepository taskAssignmentRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepo, GroupRepository groupRepo,
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
    public List<AppUserResponse> getUsers() {
        List<AppUser> appUsers = appUserRepo.findAll();
        List<AppUserResponse> appUserResponses = new ArrayList<>();
        for (AppUser user : appUsers) {
            AppUserResponse newResponse = new AppUserResponse(user);
            appUserResponses.add(newResponse);
        }
        return appUserResponses;
    }

    public AppUserResponse getUserByID(long id) {
        AppUser user = appUserRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
        return new AppUserResponse(user);
    }

    public AppUserResponse getUserByUsername(String username) {
        AppUser user = appUserRepo.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
        return new AppUserResponse(user);
    }

    public List<AppUserResponse> getUsersByGroupId(Long groupId) {
        Group group = groupRepo.getReferenceById(groupId);
        List<AppUser> appUsers = new ArrayList<>(group.getMemberships().stream().map(GroupMembership::getMember).toList());

        List<AppUserResponse> userResponses = new ArrayList<>();
        for (AppUser user : appUsers) {
            AppUserResponse response = new AppUserResponse(user);
            userResponses.add(response);
        }
        return userResponses;
    }

    //POSTS
    @Transactional
    public void registerUser(AppUserRegistrationRequest request) {
        if (appUserRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with username: " + request.getUsername());
        }
        AppUser newUser = new AppUser(request.getUsername(), null, request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        Group newGroup = new Group(request.getUsername()+"'s Group", request.getUsername() + "'s initiating group");
        GroupMembership userMembership = new GroupMembership(newGroup, newUser, GroupRole.OWNER);
        newUser.addMembership(userMembership);

        groupRepo.save(newGroup);
        appUserRepo.save(newUser);
        groupMembershipRepo.save(userMembership);
    }

    //PATCHES
    @Transactional
    public void updateUserInfo(Long id, AppUserUpdateRequest request) {
        AppUser user = appUserRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
    }

    //DELETES
    @Transactional
    public void deleteUserByID(long id) {

    }

}
