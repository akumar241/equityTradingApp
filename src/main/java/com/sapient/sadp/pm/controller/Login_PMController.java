package com.sapient.sadp.pm.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.sadp.pm.dao.PortfolioDAO;
import com.sapient.sadp.pm.model.PM;
import com.sapient.sadp.pm.model.Portfolio;
import com.sapient.sadp.pm.service.PmLoginService;
import com.sapient.sadp.pm.service.PortfolioService;

@Controller
public class Login_PMController {

    @Autowired
    private PmLoginService pmLoginService;
    
    @Autowired
    private PortfolioService portfolioService;
    


    @RequestMapping(value="/loginpm" , method=RequestMethod.POST)
    public String loginpm(@ModelAttribute("userid") String username, @ModelAttribute("password") String password,Model model, HttpSession session){
        if (username != null) {
            if(pmLoginService.validatePm(username))
            {
                if(pmLoginService.validatePassword(password))
                {   
                    PM pm = pmLoginService.getpmbyusername(username);
                    session.setAttribute("pm_id", pm.getId());
                    session.setAttribute("username", username);
                    session.setMaxInactiveInterval(300);
                    System.out.println(username+username.getClass().toString());
                    Iterable<Portfolio> portfolio = portfolioService.getAllPortfoliosByPMId((Long) session.getAttribute("pm_id"));
                    model.addAttribute("portfolio",portfolio);  
                    return "forward:/ViewPositionsbyportfolio";
                }
            }
        }
        return "LoginError";
        
    }
    
    @RequestMapping(value = "/pmverifyLogin", method = RequestMethod.GET)
    public void addUser(HttpServletResponse response, @RequestParam Map<String, String> params) {
        String username=params.get("username");
        String password=params.get("password");
        System.out.println("Verify Login called " + username + "\t" + password);
        String jsonResponse = "";
        if (!username.isEmpty()) {
            if (pmLoginService.validatePm(username)) {
                if (pmLoginService.validatePassword(password)) {
                    jsonResponse="true";
                    System.out.println("success");
                }
            }
            else {jsonResponse="false";}
        }
        else {jsonResponse="false";}
        try {
            System.out.println("sending response");
            response.getOutputStream().print(jsonResponse);
            System.out.println("response sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
