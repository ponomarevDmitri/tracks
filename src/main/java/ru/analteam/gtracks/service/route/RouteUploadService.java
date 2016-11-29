package ru.analteam.gtracks.service.route;

import org.springframework.stereotype.Service;
import ru.analteam.gtracks.model.route.Route;
import ru.analteam.gtracks.model.security.SecUser;

import java.io.InputStream;

/**
 * Created by dima-pc on 27.11.2016.
 */
@Service
public class RouteUploadService {

    public Route uploadFromStream(InputStream fileStream, SecUser user) {
        return null; //todo
    }
}
