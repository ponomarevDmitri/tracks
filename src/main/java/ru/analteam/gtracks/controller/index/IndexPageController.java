package ru.analteam.gtracks.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * Created by dima-pc on 12.02.2016.
 */
@Controller
@RequestMapping("/")
public class IndexPageController {
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index/index");//, new FreeMarkerView());
        return modelAndView;
    }
}
