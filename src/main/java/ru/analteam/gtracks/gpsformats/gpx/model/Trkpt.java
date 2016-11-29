package ru.analteam.gtracks.gpsformats.gpx.model;

import ru.analteam.gtracks.gpsformats.gpx.adapters.GpxDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by dima-pc on 28.11.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Trkpt {

    @XmlAttribute
    private Double lat;

    @XmlAttribute
    private Double lon;

    @XmlElement
    @XmlJavaTypeAdapter(GpxDateAdapter.class)
    private Date time;

    @XmlElement(name = "ele")
    private Double elevation;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }
}
