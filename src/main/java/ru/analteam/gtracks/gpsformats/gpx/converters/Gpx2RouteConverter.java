package ru.analteam.gtracks.gpsformats.gpx.converters;

import ru.analteam.gtracks.gpsformats.gpx.model.Gpx;
import ru.analteam.gtracks.gpsformats.gpx.model.Trkpt;
import ru.analteam.gtracks.model.route.GeoCoordinate;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.RoutePoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by dima-pc on 28.11.2016.
 */
public class Gpx2RouteConverter {
    public Route convert(Gpx gpx) {
        Route route = new Route();


        Collector<Trkpt, List<RoutePoint>, List<RoutePoint>> routePointListTransformer = new Collector<Trkpt, List<RoutePoint>, List<RoutePoint>>() {

            @Override
            public Supplier<List<RoutePoint>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<RoutePoint>, Trkpt> accumulator() {
                return (routePoints1, trkpt) -> {
                    RoutePoint newPoint = convertPointPartitially(trkpt);
                    if (routePoints1.size() > 0) {
                        routePoints1.get(routePoints1.size() - 1).setNextPoint(newPoint);
                    }
                    routePoints1.add(newPoint);
                };
            }

            @Override
            public BinaryOperator<List<RoutePoint>> combiner() {
                return (leftList, rightList) -> {
                    if (leftList.size() > 0 && rightList.size() > 0) {
                        leftList.get(leftList.size() - 1).setNextPoint(rightList.get(0));
                    }
                    leftList.addAll(rightList);
                    return leftList;
                };
            }

            @Override
            public Function<List<RoutePoint>, List<RoutePoint>> finisher() {
                return array -> array;
            }

            @Override
            public Set<Characteristics> characteristics() {
                HashSet<Characteristics> characteristics = new HashSet<>();
                characteristics.add(Characteristics.IDENTITY_FINISH);
                return characteristics;
            }
        };

        route.setRoutePoints(gpx.getTrk().getTrkseg().stream().collect(routePointListTransformer));
//        route.setName();

        return route;
    }

    private RoutePoint convertPointPartitially(Trkpt trkpt) {
        RoutePoint result = new RoutePoint();

        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLatitude(trkpt.getLat());
        geoCoordinate.setLongitude(trkpt.getLon());
        geoCoordinate.setElevation(trkpt.getElevation());

        result.setGeoCoordinate(geoCoordinate);
        return result;
    }

}
