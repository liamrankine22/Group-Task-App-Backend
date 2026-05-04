package com.liamrankine.taskmanager.controllers;

import com.liamrankine.taskmanager.datatransfer.requests.appuser.AppUserRegistrationRequest;
import com.liamrankine.taskmanager.datatransfer.responses.AppUserResponse;
import com.liamrankine.taskmanager.entities.AppUser;
import com.liamrankine.taskmanager.services.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUserResponse> getUsers() {
        return appUserService.getUsers();
    }

    @GetMapping("/{id}")
    public AppUserResponse getUserByID(@PathVariable Long id) {
        return appUserService.getUserByID(id);
    }

    @GetMapping("/username/{username}")
    public AppUserResponse getUserByUsername(@PathVariable String username) {
        return appUserService.getUserByUsername(username);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody AppUserRegistrationRequest request) {
        appUserService.registerUser(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserByID(@PathVariable Long id) {
        appUserService.deleteUserByID(id);
    }
}
