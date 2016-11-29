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
public class Metadata {

    @XmlElement
    @XmlJavaTypeAdapter(GpxDateAdapter.class)
    private Date time;

    @XmlElement
    private Bounds bounds;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Bounds {

        @XmlAttribute
        private Double minlat;

        @XmlAttribute
        private Double minlon;

        @XmlAttribute
        private Double maxlat;

        @XmlAttribute
        private Double maxlon;

        public Double getMinlat() {
            return minlat;
        }

        public void setMinlat(Double minlat) {
            this.minlat = minlat;
        }

        public Double getMinlon() {
            return minlon;
        }

        public void setMinlon(Double minlon) {
            this.minlon = minlon;
        }

        public Double getMaxlat() {
            return maxlat;
        }

        public void setMaxlat(Double maxlat) {
            this.maxlat = maxlat;
        }

        public Double getMaxlon() {
            return maxlon;
        }

        public void setMaxlon(Double maxlon) {
            this.maxlon = maxlon;
        }
    }
}
