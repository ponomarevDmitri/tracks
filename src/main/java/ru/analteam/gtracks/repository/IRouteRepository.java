package ru.analteam.gtracks.repository;

import ru.analteam.gtracks.model.route.Route;

import java.util.List;

/**
 * Created by dima-pc on 05.06.2016.
 */
public interface IRouteRepository {
    Route getRouteById(Long routeId);
    List<Route> getRouteListByIds(List<Long> routeIdList);
    Route create(Route route);
    Route update(Route route);
}
