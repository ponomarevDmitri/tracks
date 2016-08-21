package ru.analteam.gtracks.repository;

import ru.analteam.gtracks.model.route.UserRoutes;
import ru.analteam.gtracks.model.security.SecUser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by dima-pc on 16.08.2016.
 */
public interface IUserRoutesRepository {

    @Nonnull
    List<UserRoutes> getUserRoutesByUser(@Nullable SecUser secUser);
}
