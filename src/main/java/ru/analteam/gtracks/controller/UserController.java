package ru.analteam.gtracks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dima-pc on 14.10.2016.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("user_route_list")
    public ModelAndView userRoutes() {
        return new ModelAndView("user/user_route_list");
    }
}
