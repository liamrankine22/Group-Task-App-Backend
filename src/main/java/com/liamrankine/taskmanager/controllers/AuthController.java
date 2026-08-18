package com.liamrankine.taskmanager.controllers;

import com.liamrankine.taskmanager.datatransfer.requests.appuser.AppUserRegistrationRequest;
import com.liamrankine.taskmanager.entities.*;
import com.liamrankine.taskmanager.repositories.AppUserRepository;
import com.liamrankine.taskmanager.services.EmailService;
import com.liamrankine.taskmanager.services.UserMakerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AppUserRepository userRepo;
    private final UserMakerService userMakerService;
    private final EmailService emailService;

    public AuthController(AppUserRepository userRepo, UserMakerService userMakerService, EmailService emailService) {
        this.userRepo = userRepo;
        this.userMakerService = userMakerService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUserRegistrationRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        userMakerService.UserMaker(request.getUsername(), request.getEmail(), request.getPassword());
        emailService.sendWelcomeEmail(request.getEmail(), request.getUsername());

        return ResponseEntity.ok().build();
    }
}
