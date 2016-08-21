package ru.analteam.gtracks.controller.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.model.converter.security.Route2RouteDtoConverter;
import ru.analteam.gtracks.model.converter.security.Route2RouteDtoListConverter;
import ru.analteam.gtracks.model.converter.security.RouteDto2RouteConverter;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.service.route.RouteService;
import ru.analteam.gtracks.service.user.UserService;

import java.util.List;

/**
 * Created by dima-pc on 07.06.2016.
 */

@RestController
@RequestMapping("routes")
public class RestRouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

    private RouteDto2RouteConverter toRouteConverter = new RouteDto2RouteConverter();
    private Converter<Route, RouteDto> toRouteDtoConverter = new Route2RouteDtoConverter();
    private Converter<List<Route>, List<RouteDto>> toRouteDtoListConverter = new Route2RouteDtoListConverter();

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto routeDto){
        Route newRoute = toRouteConverter.convert(routeDto, userService.getCurrentUser());

        newRoute = routeService.createRoute(newRoute);

        routeDto.setId(newRoute.getId());

        return new ResponseEntity<>(routeDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/user")
    public ResponseEntity<List<RouteDto>> getUserRoutes(){
        List<Route> userRoutes = routeService.listUserRoutes(
                userService.getCurrentUser());

        return new ResponseEntity<>(toRouteDtoListConverter.convert(userRoutes), HttpStatus.OK);
    }
}
