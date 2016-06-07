package ru.analteam.gtracks.repository;

import ru.analteam.gtracks.model.route.Route;

/**
 * Created by dima-pc on 05.06.2016.
 */
public interface IRouteRepository {
    Route getRouteById(Long routeId);
    Route create(Route route);
}
