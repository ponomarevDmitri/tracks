package ru.analteam.gtracks.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.UserRoutes;
import ru.analteam.gtracks.model.security.SecUser;
import ru.analteam.gtracks.repository.IRouteRepository;
import ru.analteam.gtracks.repository.IUserRoutesRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dima-pc on 05.06.2016.
 */
@Service
@Transactional
public class RouteService {

    @Autowired
    private IRouteRepository routeRepository;

    @Autowired
    private IUserRoutesRepository userRoutesRepository;

    public Route getRouteById(Long id) {
        return routeRepository.getRouteById(id);
    }

    public Route createRoute(Route route) {
        return routeRepository.create(route);//todo create (done?)
    }

    public List<Route> listUserRoutes(SecUser secUser){
        List<UserRoutes> userRoutesByUser = userRoutesRepository.getUserRoutesByUser(secUser);
        return userRoutesByUser.stream().map(UserRoutes::getRoute)
                .collect(Collectors.toList());
    }
}
