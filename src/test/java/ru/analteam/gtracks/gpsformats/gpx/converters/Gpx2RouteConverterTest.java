package ru.analteam.gtracks.gpsformats.gpx.converters;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import ru.analteam.gtracks.gpsformats.gpx.model.Gpx;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;
import ru.analteam.gtracks.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dima-pc on 29.11.2016.
 */
public class Gpx2RouteConverterTest {

    @Test
    public void testConvert() throws Exception {

        Gpx2RouteConverter converter = new Gpx2RouteConverter();
        Gpx unmarshalled = TestUtils.unmarshall2Gpx(new ClassPathResource("gps_converting/gpx/ConvertFromGpxTestData.gpx"));
        Route converted = converter.convert(unmarshalled);

        assertNotNull(converted);

        List<RoutePoint> routePoints = converted.getRoutePoints();
        assertNotNull(routePoints);
        assertEquals(4, routePoints.size());

        RoutePoint routePoint = routePoints.get(0);
        assertHasGeoCoordinates(routePoint, 55.992784d, 37.246915d, 124.66319981051d);
        assertEquals(routePoint.getNextPoint(), routePoints.get(1));

        routePoint = routePoints.get(1);
        assertHasGeoCoordinates(routePoint, 55.992762d, 37.246882d, 119.17679981885d);
        assertEquals(routePoint.getNextPoint(), routePoints.get(2));

        routePoint = routePoints.get(2);
        assertHasGeoCoordinates(routePoint, 55.992787d, 37.246817d, 126.1871998082d);
        assertEquals(routePoint.getNextPoint(), routePoints.get(3));

        routePoint = routePoints.get(3);
        assertHasGeoCoordinates(routePoint, 55.992752, 37.246749d, 168.24959974426d);
    }

    private void assertHasGeoCoordinates(RoutePoint routePoint, double expectedLatitude, double expectedLongitude, double expectedElevation) {
        assertEquals(expectedLatitude, routePoint.getGeoCoordinate().getLatitude(), 0.0000001);
        assertEquals(expectedLongitude, routePoint.getGeoCoordinate().getLongitude(), 0.0000001);
        assertEquals(expectedElevation, routePoint.getGeoCoordinate().getElevation(), 0.00000000001);
    }

}