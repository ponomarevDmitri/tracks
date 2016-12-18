package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.dto.RoutePointDto;
import ru.analteam.gtracks.model.route.GeoCoordinate;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;
import ru.analteam.gtracks.model.route.RoutePointDescription;
import ru.analteam.gtracks.model.security.SecUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima-pc on 07.06.2016.
 */
public class RouteDto2RouteConverter implements Converter<RouteDto, Route> {

    @Nonnull
    public Route convert(@Nonnull RouteDto source) {
        Route route = new Route();

        route.setShortDescription(source.getShortDescription());
        route.setDescription(source.getDescription());
        route.setName(source.getName());

        route.setRoutePoints(convertRoutePoints(source.getPoints(), route));

        return route;
    }

    @Nonnull
    public Route convert(@Nonnull RouteDto source,@Nullable SecUser owner) {
        Route route = convert(source);
        route.setUser(owner);
        return route;
    }

    private List<RoutePoint> convertRoutePoints(List<RoutePointDto> routePointDtoList, Route route) {
        List<RoutePoint> result = new ArrayList<RoutePoint>();

        if (routePointDtoList != null) {
            for (RoutePointDto routePointDto : routePointDtoList) {
                RoutePoint routePoint = convertRoutePoint(routePointDto, route);
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

    private RoutePoint convertRoutePoint(RoutePointDto routePointDto, Route route) {
        RoutePoint result = new RoutePoint();

        //todo: add double truncation
        result.setGeoCoordinate(new GeoCoordinate(routePointDto.getLat(), routePointDto.getLng()));
        result.setPointDescription(createPointDescription(routePointDto, result));
//        result.setDescription(routePointDto.getDescription());
//        result.setShortDescription(routePointDto.getShortDescription());
        result.setRoute(route);

        return result;
    }

    private RoutePointDescription createPointDescription(RoutePointDto routePointDto, RoutePoint routePoint) {
        RoutePointDescription result = new RoutePointDescription();

        result.setDescription(routePointDto.getDescription());
        result.setShortDescription(routePointDto.getShortDescription());
        result.setRoutePoint(routePoint);

        return result;
    }
}
