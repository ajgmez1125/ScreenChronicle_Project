package org.humber.project.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.humber.project.dto.UserDto;
import org.humber.project.services.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Hidden
public class UserController {

    private final ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    public UserController(ApplicationUserDetailsService applicationUserDetailsService) {
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @PostMapping
    public boolean createUser(@RequestBody UserDto userDto) {
        return applicationUserDetailsService.registerUser(userDto);
    }
}