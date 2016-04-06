package ru.analteam.gtracks.controller.admin;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.ServletContext;

/**
 * Created by dima-pc on 10.02.2016.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("admin/")
@Deprecated //but now using
public class AdminController {

    @RequestMapping("index")
    public ModelAndView admin(ServletContext servletContext){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new JstlView("adminPage.jsp"));//todo change



        return modelAndView;
    }
}
