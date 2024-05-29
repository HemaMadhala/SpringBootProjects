package com.app.FormValidation.controller;

import com.app.FormValidation.model.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    @GetMapping("/")
    public String home(Model model){
        System.out.println("loging the form");
        model.addAttribute("logindata", new LoginData());
        return "form";
    }
}
