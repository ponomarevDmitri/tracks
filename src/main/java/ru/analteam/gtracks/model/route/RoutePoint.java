package ru.analteam.gtracks.model.route;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by dima-pc on 05.06.2016.
 */
@Entity
@Table(name = "route_pont")
public class RoutePoint {
    private Long id;
    private Route route;

    private GeoCoordinate geoCoordinate;
    private RoutePoint nextPoint;

    private String shortDescription;
    private String description;

    private DateTime moment;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Route.class)
    @JoinColumn(name = "route_id")
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @ManyToOne(targetEntity = GeoCoordinate.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "geo_coordinate_id")
    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }

    @ManyToOne(targetEntity = RoutePoint.class)
    @JoinColumn(name = "next_route_point")
    public RoutePoint getNextPoint() {
        return nextPoint;
    }

    public void setNextPoint(RoutePoint nextPoint) {
        this.nextPoint = nextPoint;
    }

    @Column(name  = "short_description")
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name="moment")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    public DateTime getMoment() {
        return moment;
    }

    public void setMoment(DateTime moment) {
        this.moment = moment;
    }
}
