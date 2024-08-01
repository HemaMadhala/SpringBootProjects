package com.app.contact.contactmanager.controller;

import com.app.contact.contactmanager.dao.UserRepo;
import com.app.contact.contactmanager.helper.Message;
import com.app.contact.contactmanager.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/")
    public static String home(Model model){
        model.addAttribute("title","home-smart contact manager");
        return "home";
    }

    @GetMapping("/about")
    public static String about(Model model){
        model.addAttribute("title","about page");
        return "about";
    }

    @GetMapping("/signup")
    public static String signup(Model model){
        model.addAttribute("title","Sign up - contact manager");
        model.addAttribute("user", new User()) ;
        return "signup";
    }
    @PostMapping("/do_signup")
    public String registerUse(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session){
        try {
            if(!agreement){
                System.out.println("You have not agreed the terms and conditions");
                throw new Exception("You have not agreed the terms and conditions");
            }
            user.setEnabled(true);
            user.setRole("ROLE_USER");
            userRepo.save(user);
            session.setAttribute("message", new Message("Success!!","alert-Success"));
            System.out.println("Agreement: "+ agreement);
            System.out.println("User: "+ user);

        }
        catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message", new Message("Error!!"+ e.getMessage(),"alert-error"));
        }
        return "signup";
    }
}
