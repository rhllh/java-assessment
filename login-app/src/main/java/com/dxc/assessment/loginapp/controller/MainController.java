package com.dxc.assessment.loginapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dxc.assessment.loginapp.model.CustomUserDetails;

@Controller
public class MainController {

    @GetMapping("/")
    public String getGreetingPage(Authentication auth, Model model) {
        model.addAttribute("isAuth", auth != null);
        if (auth != null) {
            CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal();
            model.addAttribute("userDetails", cud);
        }
        return "hello";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/restricted")
    public String getRestrictedPage() {
        return "restricted";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }

}
