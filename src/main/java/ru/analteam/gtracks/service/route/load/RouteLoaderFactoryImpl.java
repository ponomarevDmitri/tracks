package ru.analteam.gtracks.service.route.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dima-pc on 05.12.2016.
 */
@Service
public class RouteLoaderFactoryImpl implements IRouteLoaderFactory {

    @Autowired
    private GpxRouteLoader gpxRouteLoader;

    @Override
    public IRouteLoader getRouteLoader(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename.endsWith(".gpx")) {
            return gpxRouteLoader;
        }
        //todo: add logging and rethrowing
        throw new IllegalArgumentException("Extension of file '" + originalFilename + "' is unsupported");
    }
}
