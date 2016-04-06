package ru.analteam.gtracks.controller.index;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.ServletContext;
import java.util.Collection;

/**
 * Created by dima-pc on 12.02.2016.
 */
@Controller
@RequestMapping("/")
public class IndexPageController {
    
    private static final String ADMIN_AUTHORITY_NAME = "ROLE_ADMIN";

    //may need to use ServletContext context
    @RequestMapping("/index")
    public ModelAndView index(@AuthenticationPrincipal UserDetails userDetails){
        ModelAndView modelAndView = new ModelAndView("index/index");//, new FreeMarkerView());

        String viewName;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (isAdmin(authorities)) {
            viewName = "index/adminIndex";
        } else {
            viewName = "index/index";
        }

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    private boolean isAdmin(Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(ADMIN_AUTHORITY_NAME)) {
                return true;
            }
        }
        return false;
    }
}
