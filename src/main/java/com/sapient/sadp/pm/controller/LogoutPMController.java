package com.sapient.sadp.pm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
public class LogoutPMController {
    
    
    
    @RequestMapping(value = "/logoutpm", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return "logout";
    }
}
