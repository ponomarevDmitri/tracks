package ru.analteam.gtracks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dima-pc on 06.02.2016.
 */
@Controller
@RequestMapping("/testLogin")
public class TestLoginController {

    @RequestMapping("/test")
    public String testLogin(){
        return "testLogin";
    }
}
