package com.liamrankine.taskmanager.services;

import com.liamrankine.taskmanager.entities.*;
import com.liamrankine.taskmanager.enumerations.GroupRole;
import com.liamrankine.taskmanager.repositories.AppUserRepository;
import com.liamrankine.taskmanager.repositories.GroupMembershipRepository;
import com.liamrankine.taskmanager.repositories.GroupRepository;
import com.liamrankine.taskmanager.repositories.TaskRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserMakerService {
    private final AppUserRepository userRepo;
    private final GroupRepository groupRepo;
    private final TaskRepository taskRepo;
    private final PasswordEncoder encoder;
    private final GroupMembershipRepository groupMembershipRepository;

    public UserMakerService(AppUserRepository userRepo, GroupRepository groupRepo, TaskRepository taskRepo, PasswordEncoder encoder, GroupMembershipRepository groupMembershipRepository)
    {
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
        this.taskRepo = taskRepo;
        this.encoder = encoder;
        this.groupMembershipRepository = groupMembershipRepository;
    }

    public AppUser UserMaker(String name, String email, String password) {
        AppUser user = new AppUser();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));

        userRepo.save(user);

        Group group = new Group();
        group.setName(name + "'s Group");

        groupRepo.save(group);

        GroupMembership membership = new GroupMembership(group, user, GroupRole.OWNER);
        groupMembershipRepository.save(membership);
        group.addMembership(membership);
        user.addMembership(membership);

        userRepo.save(user);

        Task openerTask = new Task("Welcome to Task Manager!", "Click on the ellipses button to view all information on this task!", "in-progress", LocalDate.parse("2027-01-01"));
        openerTask.setGroup(group);
        openerTask.setCreatedBy(user);
        openerTask.setCreatedDate(LocalDate.now());
        openerTask.addTaskAssignment(new TaskAssignment(user, openerTask, LocalDate.now()));
        taskRepo.save(openerTask);

        return user;
    }
}
