package ru.analteam.gtracks.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.analteam.gtracks.model.route.GeoCoordinate;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima-pc on 05.06.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @Test
    public void testCreate() throws Exception {
        Route route = new Route();
        List<RoutePoint> points = new ArrayList<RoutePoint>();

        RoutePoint e = new RoutePoint();
        e.setGeoCoordinate(new GeoCoordinate(12.3212, 1232.131));
        points.add(e);

        e = new RoutePoint();
        e.setGeoCoordinate(new GeoCoordinate(12.3212, 1232.131));
        points.add(e);

        points.get(0).setNextPoint(points.get(1));

        route.setRoutePoints(points);

        route.setDescription("some description");
        route.setShortDescription("short desrciption");


    }
}