package ru.analteam.gtracks.model.route;

import javax.persistence.*;

/**
 * Created by dima-pc on 04.06.2016.
 */
@Entity
@Table(name = "geo_coordinate")
public class GeoCoordinate {

    /**
     CREATE TABLE `coords` (
     `lat` FLOAT( 10, 6 ) NOT NULL ,
     `lng` FLOAT( 10, 6 ) NOT NULL ,
     ) ENGINE = MYISAM ;
     */


    private Long id;

    private Double latitude;
    private Double longitude;
    private Double elevation;

    public GeoCoordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoCoordinate(Double latitude, Double longitude, Double elevation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public GeoCoordinate() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "elevation")
    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }
}
