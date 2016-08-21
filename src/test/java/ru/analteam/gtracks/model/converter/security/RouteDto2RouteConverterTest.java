package ru.analteam.gtracks.model.converter.security;

import org.junit.Assert;
import org.junit.Test;
import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.dto.RoutePointDto;
import ru.analteam.gtracks.dto.UserDto;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for {@link RouteDto2RouteConverter}
 * Created by dima-pc on 25.06.2016.
 */
public class RouteDto2RouteConverterTest {
    @Test
    public void testConvert() throws Exception {
        RouteDto2RouteConverter converter = new RouteDto2RouteConverter();

        RouteDto source = new RouteDto();

        source.setDescription("description");
        source.setShortDescription("short description");
        source.setName("name");
        source.setUser(new UserDto("username"));

        ArrayList<RoutePointDto> routePointDtos = new ArrayList<RoutePointDto>();
        RoutePointDto routePointDto = new RoutePointDto();
        routePointDto.setShortDescription("first point");
        routePointDto.setDescription("first point description");
        routePointDto.setLat(11.111111d);
        routePointDto.setLng(111.222222d);
        routePointDtos.add(routePointDto);

        routePointDto = new RoutePointDto();
        routePointDto.setShortDescription("second point");
        routePointDto.setDescription("second point description");
        routePointDto.setLat(22.333333d);
        routePointDto.setLng(122.333333d);
        routePointDtos.add(routePointDto);

        source.setPoints(routePointDtos);


        Route converted = converter.convert(source);


        Assert.assertNotNull(converted);
        Assert.assertEquals("description", converted.getDescription());
        Assert.assertEquals("short description", converted.getShortDescription());
        Assert.assertEquals("name", converted.getName());

        List<RoutePoint> convertedRoutePointList = converted.getRoutePoints();
        Assert.assertNotNull(convertedRoutePointList);
        Assert.assertEquals(2, convertedRoutePointList.size());

        RoutePoint convertedRoutePoint = convertedRoutePointList.get(0);
        Assert.assertEquals("first point", convertedRoutePoint.getShortDescription());
        Assert.assertEquals("first point description", convertedRoutePoint.getDescription());
        Assert.assertEquals(Double.valueOf(11.111111d), convertedRoutePoint.getGeoCoordinate().getLatitude());
        Assert.assertEquals(Double.valueOf(111.222222d), convertedRoutePoint.getGeoCoordinate().getLongitude());

        convertedRoutePoint = convertedRoutePointList.get(1);
        Assert.assertEquals("second point", convertedRoutePoint.getShortDescription());
        Assert.assertEquals("second point description", convertedRoutePoint.getDescription());
        Assert.assertEquals(Double.valueOf(22.333333d), convertedRoutePoint.getGeoCoordinate().getLatitude());
        Assert.assertEquals(Double.valueOf(122.333333d), convertedRoutePoint.getGeoCoordinate().getLongitude());
    }
}