package ru.analteam.gtracks.controller.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.service.route.RouteService;
import ru.analteam.gtracks.service.security.RouteSecutiryCheckService;
import ru.analteam.gtracks.service.user.UserService;

/**
 * Created by dima-pc on 23.10.2016.
 */
@RequestMapping("user/route")
public class RouteController {

    @Autowired
    private RouteSecutiryCheckService routeSecutiryCheckService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

    @RequestMapping("edit/{id}")
    public ModelAndView editRoute(@PathVariable("id") Long routeId){
        Route routeById = routeService.getRouteById(routeId);


        routeSecutiryCheckService.assertAccessToRead(userService.getCurrentUser(),
                routeById);
        return new ModelAndView("viewName");//todo
    }
}
