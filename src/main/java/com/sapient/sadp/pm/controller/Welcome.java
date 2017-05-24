package com.sapient.sadp.pm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Welcome {

    @RequestMapping("/welcome")
    public String welcome(Model model, HttpSession session) {
        System.out.println("Welcome");
        session.removeAttribute("username");
        session.invalidate();
        return "login";
    }
}
