package ru.analteam.gtracks.repository;

import org.springframework.stereotype.Repository;
import ru.analteam.gtracks.model.route.UserRoutes;
import ru.analteam.gtracks.model.security.SecUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima-pc on 16.08.2016.
 */
@Repository
public class UserRoutesRepository implements IUserRoutesRepository {

    @PersistenceContext
    private EntityManager em;

    @Nonnull
    public List<UserRoutes> getUserRoutesByUser(@Nullable SecUser secUser) {
        if (secUser == null) {
            return new ArrayList<>();
        }

        TypedQuery<UserRoutes> query = em.createQuery("select ur from UserRoutes ur where ur.user = :user", UserRoutes.class);
        query.setParameter("user", secUser);
        return query.getResultList();
    }
}
