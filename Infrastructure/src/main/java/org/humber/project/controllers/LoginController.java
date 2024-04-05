package org.humber.project.controllers;

import org.humber.project.services.ApplicationUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController
{
    private ApplicationUserDetailsService userDetailsService;

    public LoginController(ApplicationUserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("username") String username, 
                        @RequestParam("password") String password)
    {
        return "redirect:/index.html";
    }
}
