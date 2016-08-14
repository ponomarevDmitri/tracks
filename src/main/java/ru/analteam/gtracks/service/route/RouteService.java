package ru.analteam.gtracks.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.security.SecUser;
import ru.analteam.gtracks.repository.IRouteRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima-pc on 05.06.2016.
 */
@Service
@Transactional
public class RouteService {

    @Autowired
    private IRouteRepository routeRepository;

    public Route getRouteById(Long id) {
        return routeRepository.getRouteById(id);//todo
    }

    public Route createRoute(Route route) {
        return routeRepository.create(route);//todo create
    }

    public List<Route> listUserRoutes(SecUser secUser){
//        List
        return new ArrayList<Route>();//todo
    }
}
