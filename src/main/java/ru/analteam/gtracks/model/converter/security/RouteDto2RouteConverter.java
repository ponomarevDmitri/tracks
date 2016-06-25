package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.dto.RoutePointDto;
import ru.analteam.gtracks.model.route.GeoCoordinate;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima-pc on 07.06.2016.
 */
public class RouteDto2RouteConverter implements Converter<RouteDto, Route> {
    public Route convert(RouteDto source) {
        Route route = new Route();

        route.setShortDescription(source.getShortDescription());
        route.setDescription(source.getDescription());
        route.setName(source.getName());

        route.setRoutePoints(convertRoutePoints(source.getRoute()));

        return route;
    }

    private List<RoutePoint> convertRoutePoints(List<RoutePointDto> routePointDtoList) {
        List<RoutePoint> result = new ArrayList<RoutePoint>();

        if (routePointDtoList != null) {
            for (RoutePointDto routePointDto : routePointDtoList) {
                RoutePoint routePoint = convertRoutePoint(routePointDto);
                result.add(routePoint);
            }

            //set next points in route point elements
            RoutePoint lastPoint = null;
            for (RoutePoint routePoint : result) {
                if (lastPoint != null) {
                    lastPoint.setNextPoint(routePoint);
                }
                lastPoint = routePoint;
            }
        }

        return result;
    }

    private RoutePoint convertRoutePoint(RoutePointDto routePointDto) {
        RoutePoint result = new RoutePoint();

        //todo: add double truncation
        result.setGeoCoordinate(new GeoCoordinate(routePointDto.getLatitude(), routePointDto.getLongitude()));
        result.setDescription(routePointDto.getDescription());
        result.setShortDescription(routePointDto.getShortDescription());

        return result;
    }
}
