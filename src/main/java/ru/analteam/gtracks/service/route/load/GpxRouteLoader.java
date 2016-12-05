package ru.analteam.gtracks.service.route.load;

import org.springframework.stereotype.Service;
import ru.analteam.gtracks.gpsformats.gpx.converters.Gpx2RouteConverter;
import ru.analteam.gtracks.gpsformats.gpx.model.Gpx;
import ru.analteam.gtracks.model.route.Route;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;

/**
 * Created by dima-pc on 05.12.2016.
 */
@Service
public class GpxRouteLoader implements IRouteLoader {
    Gpx2RouteConverter gpx2RouteConverter = new Gpx2RouteConverter();

    @Override
    public Route loadRoute(InputStream inputStream) {
        try {
            Gpx gpxModel = ((Gpx) JAXBContext.newInstance(Gpx.class).createUnmarshaller().unmarshal(inputStream));
            return gpx2RouteConverter.convert(gpxModel);
        } catch (JAXBException e) {
            e.printStackTrace();//todo rethrow properly
            throw new RuntimeException(e);
        }
    }
}
