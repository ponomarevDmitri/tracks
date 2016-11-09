package ru.analteam.gtracks.service.security;

import org.springframework.stereotype.Service;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.security.SecUser;

import javax.validation.constraints.NotNull;

/**
 * Created by dima-pc on 23.10.2016.
 */
@Service
public class RouteSecutiryCheckService {

    public void assertAccessToRead(@NotNull SecUser user,
                                   @NotNull Route route) {
        // todo checks that user has read access to route
    }
}
