package ru.analteam.gtracks.gpsformats.gpx.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by dima-pc on 28.11.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Trk {

    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "trkseg")
    @XmlElement(name = "trkpt")
    private List<Trkpt> trkseg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trkpt> getTrkseg() {
        return trkseg;
    }

    public void setTrkseg(List<Trkpt> trkseg) {
        this.trkseg = trkseg;
    }
}
