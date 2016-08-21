package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.dto.RoutePointDto;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dima-pc on 16.08.2016.
 */
public class Route2RouteDtoConverter implements Converter<Route, RouteDto> {
    private SecUser2UserDtoSecuredConverter securedUserConverter = new SecUser2UserDtoFullConverter();

    public RouteDto convert(Route source) {
        RouteDto result = new RouteDto();

        result.setId(source.getId());
        result.setName(source.getName());
        result.setShortDescription(source.getShortDescription());
        result.setDescription(source.getDescription());
        result.setPoints(convertRoutePoints(source.getRoutePoints()));
        result.setUser(securedUserConverter.convert(source.getUser()));

        return result;
    }

    private List<RoutePointDto> convertRoutePoints(List<RoutePoint> routePoints) {
        List<RoutePointDto> resultList = new ArrayList<>();

        if (routePoints != null && !routePoints.isEmpty()) {
            RoutePoint firstPoint = findFirstPoint(routePoints);
            do {
                RoutePointDto rpd = new RoutePointDto();
                rpd.setShortDescription(firstPoint.getShortDescription());
                rpd.setDescription(firstPoint.getDescription());
                rpd.setLat(firstPoint.getGeoCoordinate().getLatitude());
                rpd.setLng(firstPoint.getGeoCoordinate().getLongitude());
                resultList.add(rpd);

                firstPoint = firstPoint.getNextPoint();
            } while (firstPoint != null);
        }

        return resultList;
    }

    private RoutePoint findFirstPoint(final List<RoutePoint> routePoints) {
        List<RoutePoint> nonFirstPoints = routePoints.stream().map(RoutePoint::getNextPoint)
                .collect(Collectors.toList());
        return routePoints.stream().filter(point -> !nonFirstPoints.contains(point)).findFirst().get();
    }
}
