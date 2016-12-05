package ru.analteam.gtracks.service.route.load;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dima-pc on 05.12.2016.
 */
public interface IRouteLoaderFactory {
    IRouteLoader getRouteLoader(MultipartFile multipartFile);
}
