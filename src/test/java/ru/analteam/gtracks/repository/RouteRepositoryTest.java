package ru.analteam.gtracks.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.analteam.gtracks.model.route.GeoCoordinate;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;
import ru.analteam.gtracks.model.route.RoutePointDescription;

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
    @Rollback
    @Transactional
    public void testCreateAndFetchCoordinates() throws Exception {
        Route route = new Route();
        List<RoutePoint> points = new ArrayList<>();

        RoutePoint e = new RoutePoint();
        e.setGeoCoordinate(new GeoCoordinate(12.3212, 1232.131, 12.0));
        points.add(e);

        e = new RoutePoint();
        e.setGeoCoordinate(new GeoCoordinate(12.3212, 1232.131));
        e.setPointDescription(new RoutePointDescription("short descr", "descr", e));
        points.add(e);

        points.get(0).setNextPoint(points.get(1));

        route.setRoutePoints(points);

        route.setDescription("some description");
        route.setShortDescription("short desrciption");


        Route createdRoute = routeRepository.create(route);
        route = routeRepository.getRouteById(createdRoute.getId());


        Assert.assertNotNull(route);
        Assert.assertTrue(route.getRoutePoints().get(0).getGeoCoordinate().getLatitude().equals(12.3212));
        Assert.assertTrue(route.getRoutePoints().get(0).getGeoCoordinate().getElevation().equals(12.0));
    }
}