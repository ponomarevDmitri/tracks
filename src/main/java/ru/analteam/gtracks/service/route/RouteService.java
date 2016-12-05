package ru.analteam.gtracks.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.analteam.gtracks.exception.AccessToRouteDenied;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.route.UserRoutes;
import ru.analteam.gtracks.model.security.SecUser;
import ru.analteam.gtracks.repository.IRouteRepository;
import ru.analteam.gtracks.repository.IUserRoutesRepository;
import ru.analteam.gtracks.service.route.load.IRouteLoaderFactory;

import javax.annotation.Nullable;
import java.io.IOException;
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

    @Autowired
    private IRouteLoaderFactory routeLoaderFactory;

    @Nullable
    public Route getRouteByIdWithPermissionCheck(Long id, SecUser user) throws AccessToRouteDenied {
        Route routeById = getRouteById(id);

        if (!routeById.getUser().getId().equals(user.getId())) {
            throw new AccessToRouteDenied();
        }

        return routeById;
    }

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

    public Route loadRoute(MultipartFile multipartFile, SecUser user) {
        try {

            Route route = routeLoaderFactory.getRouteLoader(multipartFile).loadRoute(multipartFile.getInputStream());
            route.setUser(user);

            return routeRepository.create(route);
        } catch (IOException e) {
            throw new RuntimeException(e);//todo rethrow properly
        }
    }
}
