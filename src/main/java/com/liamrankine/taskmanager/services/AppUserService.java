package com.liamrankine.taskmanager.services;

import com.liamrankine.taskmanager.datatransfer.requests.appuser.AppUserRegistrationRequest;
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

    //POSTS
    @Transactional
    public void registerUser(AppUserRegistrationRequest request) {
        if (appUserRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with username: " + request.getUsername());
        }
        AppUser newUser = new AppUser(request.getUsername(), null, request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        Group newGroup = new Group(request.getUsername()+"'s Group");
        GroupMembership userMembership = new GroupMembership(newGroup, newUser, GroupRole.OWNER);
        newUser.addMembership(userMembership);

        groupRepo.save(newGroup);
        appUserRepo.save(newUser);
        groupMembershipRepo.save(userMembership);
    }

    //PUTS

    //PATCHES

    //DELETES
    @Transactional
    public void deleteUserByID(long id) {

    }

}
