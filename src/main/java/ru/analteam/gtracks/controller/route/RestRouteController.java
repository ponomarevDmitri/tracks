package ru.analteam.gtracks.controller.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.exception.AccessToRouteDenied;
import ru.analteam.gtracks.model.converter.security.Route2RouteDtoConverter;
import ru.analteam.gtracks.model.converter.security.Route2RouteDtoListConverter;
import ru.analteam.gtracks.model.converter.security.RouteDto2RouteConverter;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.security.SecUser;
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

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto routeDto) {
        Route newRoute = toRouteConverter.convert(routeDto, userService.getCurrentUser());

        newRoute = routeService.createRoute(newRoute);

        routeDto.setId(newRoute.getId());

        return new ResponseEntity<>(routeDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDto> saveRoute(@RequestBody RouteDto routeDto,
                                              @PathVariable(name = "id") Long routeId) {
        Route newTransinetRoute = toRouteConverter.convert(routeDto, userService.getCurrentUser());

        Route updatedRoute = routeService.mergeAndUpdate(routeId, newTransinetRoute);

        return new ResponseEntity<>(toRouteDtoConverter.convert(updatedRoute), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RouteDto> getRoute(@PathVariable("id") Long routeId) {
        ResponseEntity<RouteDto> result;
        try {
            SecUser currentUser = userService.getCurrentUser();
            Route foundUserRoute = routeService.getRouteByIdWithPermissionCheck(routeId, currentUser);
            if (foundUserRoute != null) {
                result = new ResponseEntity<>(toRouteDtoConverter.convert(foundUserRoute), HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AccessToRouteDenied accessToRouteDenied) {
            result = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return result;
    }

    @RequestMapping(value = "/user")
    public ResponseEntity<List<RouteDto>> getUserRoutes() {
        List<Route> userRoutes = routeService.listUserRoutes(
                userService.getCurrentUser());

        return new ResponseEntity<>(toRouteDtoListConverter.convert(userRoutes), HttpStatus.OK);
    }
}
