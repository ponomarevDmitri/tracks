package ru.analteam.gtracks.repository;

import org.springframework.stereotype.Repository;
import ru.analteam.gtracks.model.route.Route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for storing routes
 * Created by dima-pc on 05.06.2016.
 */
@Repository
public class RouteRepository implements IRouteRepository {

    @PersistenceContext
    private EntityManager em;


    public Route getRouteById(Long routeId) {
        TypedQuery<Route> routeQuery = em.createQuery("select route from Route route where route.id =:id", Route.class);
        routeQuery.setParameter("id", routeId);

        List<Route> resultList = routeQuery.getResultList();
        if (resultList != null) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public List<Route> getRouteListByIds(List<Long> routeIdList) {
        TypedQuery<Route> routeQuery = em.createQuery("select route from Route route where route.id in :idList", Route.class);
        routeQuery.setParameter("idList", routeIdList);
        return routeQuery.getResultList();
    }

    public Route create(Route route){
        em.persist(route);
        em.flush();
//        route = em.merge(route);
        return route;
    }

}
