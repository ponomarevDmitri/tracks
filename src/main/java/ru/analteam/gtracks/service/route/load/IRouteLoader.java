package ru.analteam.gtracks.service.route.load;

import ru.analteam.gtracks.model.route.Route;

import java.io.InputStream;

/**
 * Created by dima-pc on 05.12.2016.
 */
public interface IRouteLoader {
    Route loadRoute(InputStream inputStream);
}
