package ru.analteam.gtracks.controller.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.model.converter.security.RouteDto2RouteConverter;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.service.route.RouteService;

/**
 * Created by dima-pc on 07.06.2016.
 */

@RestController
@RequestMapping("routes")
public class RestRouteController {

    @Autowired
    private RouteService routeService;

    private Converter<RouteDto, Route> toRouteConverter = new RouteDto2RouteConverter();

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto routeDto){
        Route newRoute = toRouteConverter.convert(routeDto);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newRoute = routeService.createRoute(newRoute);

        routeDto.setId(newRoute.getId());

        return new ResponseEntity<RouteDto>(routeDto, HttpStatus.OK);
    }
}
