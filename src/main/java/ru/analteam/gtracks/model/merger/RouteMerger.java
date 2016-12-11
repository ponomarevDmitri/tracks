package ru.analteam.gtracks.model.merger;

import ru.analteam.gtracks.dto.RouteDto;
import ru.analteam.gtracks.dto.RoutePointDto;
import ru.analteam.gtracks.model.route.Route;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by dima-pc on 11.12.2016.
 */
public class RouteMerger {

    /**
     * Merges state of 'from' to 'into' (Notice: if into has some id, hence it will not be overrided)
     * @param into existing route
     * @param from route dto with additional info
     */
    public void merge(@Nonnull Route into,@Nullable RouteDto from) {
        if (from != null) {
            doMerge(into, from);
        }
    }

    /**
     * Merges state of 'from' to 'into' (Notice: if into has some id, hence it will not be overrided)
     * @param into existing route
     * @param from route with additional info
     */
    public void merge(@Nonnull Route into,@Nullable Route from) {
        if (from != null) {
            doMerge(into, from);
        }
    }

    private void doMerge(@Nonnull Route into,@Nonnull Route from) {
        if (into.getId() == null) {
            into.setId(from.getId());
        }

        into.setShortDescription(from.getShortDescription());
        into.setDescription(from.getDescription());
        into.setName(from.getName());

        mergeRoutePoints(into, from);
    }

    private void mergeRoutePoints(Route into, Route from) {
        //todo
    }

    private void doMerge(@Nonnull Route into,@Nonnull RouteDto from) {
        if (into.getId() == null) {
            into.setId(from.getId());
        }

        into.setShortDescription(from.getShortDescription());
        into.setDescription(from.getDescription());
        into.setName(from.getName());

        mergeRoutePoints(from.getPoints(), into);
    }

    private void mergeRoutePoints(List<RoutePointDto> points, Route into) {
        //todo
    }
}
