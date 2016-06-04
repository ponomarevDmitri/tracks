package ru.analteam.gtracks.model.route;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Id
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
}
