package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.model.route.Route;

/**
 * Created by dima-pc on 07.06.2016.
 */
public class RouteDto2RouteConverter implements Converter<RouteDto, Route> {
    public Route convert(RouteDto source) {
        Route route = new Route();

        route.setShortDescription(source.getShortDescription());
        route.setDescription(source.getDescription());
        route.setName(source.getName());

        return route;//todo
    }
}
