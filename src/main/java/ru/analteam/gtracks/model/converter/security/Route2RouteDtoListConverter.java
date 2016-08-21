package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.model.route.Route;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts Route entity object to appropriate DTO object
 * Created by dima-pc on 22.08.2016.
 */
@SuppressWarnings("Convert2streamapi")
public class Route2RouteDtoListConverter implements Converter<List<Route>, List<RouteDto>> {
    private Route2RouteDtoConverter route2RouteDtoConverter = new Route2RouteDtoConverter();

    @Override
    @Nonnull
    public List<RouteDto> convert(@Nullable List<Route> source) {
        List<RouteDto> resultList = new ArrayList<>();

        if (source != null) {
            for (Route route : source) {
                resultList.add(route2RouteDtoConverter.convert(route));
            }
        }

        return resultList;
    }
}
