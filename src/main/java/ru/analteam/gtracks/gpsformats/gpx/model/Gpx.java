package ru.analteam.gtracks.gpsformats.gpx.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dima-pc on 28.11.2016.
 */
@XmlRootElement(name = "gpx")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gpx {
    @XmlElement(name = "metadata")
    private Metadata metadata;

    @XmlElement(name = "trk")
    private Trk trk;


    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Trk getTrk() {
        return trk;
    }

    public void setTrk(Trk trk) {
        this.trk = trk;
    }
}
