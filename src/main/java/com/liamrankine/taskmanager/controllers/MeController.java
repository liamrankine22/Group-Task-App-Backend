package com.liamrankine.taskmanager.controllers;

import com.liamrankine.taskmanager.datatransfer.responses.AppUserResponse;
import com.liamrankine.taskmanager.entities.AppUser;
import com.liamrankine.taskmanager.repositories.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class MeController {

    private final AppUserRepository appUserRepo;

    public MeController(AppUserRepository appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @GetMapping("/me")
    public AppUserResponse me(Authentication auth) {
        if (auth == null || auth.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        String username = auth.getName();

        AppUser user = appUserRepo.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return new AppUserResponse(user);
    }
}