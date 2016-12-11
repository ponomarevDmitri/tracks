package ru.analteam.gtracks.controller.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.security.SecUser;
import ru.analteam.gtracks.service.route.RouteService;
import ru.analteam.gtracks.service.security.RouteSecutiryCheckService;
import ru.analteam.gtracks.service.user.UserService;

import java.util.HashMap;

/**
 * Created by dima-pc on 23.10.2016.
 */
@Controller
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

        return getEditPageModelAndView(routeById);
    }

    @RequestMapping("create")
    public ModelAndView createRoute(){

        routeSecutiryCheckService.assertAccessToCreate(userService.getCurrentUser());

        return new ModelAndView("/user/routes/create_route");
    }

    @RequestMapping("load")
    public ModelAndView loadRoute(){

        routeSecutiryCheckService.assertAccessToCreate(userService.getCurrentUser());

        return new ModelAndView("/user/routes/load_route");
    }

    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.PUT}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView uploadRouteMultipart(@RequestParam("routeData") MultipartFile routeDataFile) {
        SecUser currentUser = userService.getCurrentUser();
        routeSecutiryCheckService.assertAccessToCreate(currentUser);

        Route route = routeService.loadRoute(routeDataFile, currentUser);

        return getEditPageModelAndView(route);
    }

    private ModelAndView getEditPageModelAndView(final Route route) {
        return new ModelAndView("/user/routes/edit_route", new HashMap<String, Object>(){{
            put("route", route);
        }});
    }
}
