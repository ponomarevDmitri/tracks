package ru.analteam.gtracks.utils;

import org.springframework.core.io.ClassPathResource;
import ru.analteam.gtracks.gpsformats.gpx.model.Gpx;

import javax.xml.bind.JAXBContext;

/**
 * Created by dima-pc on 30.11.2016.
 */
public class TestUtils {
    public static Gpx unmarshall2Gpx(ClassPathResource classPathResource) {
        try {
            return (Gpx) JAXBContext.newInstance(Gpx.class).createUnmarshaller().unmarshal(classPathResource.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
