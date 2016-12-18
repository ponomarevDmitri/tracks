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

    private RoutePointDescription pointDescription;

    private DateTime moment;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "routePoint")
    public RoutePointDescription getPointDescription() {
        return pointDescription;
    }

    public void setPointDescription(RoutePointDescription pointDescription) {
        this.pointDescription = pointDescription;
    }

    @Transient
    public String getShortDescription() {
        return getPointDescription().getShortDescription();
    }

    @Transient
    public String getDescription() {
        return getPointDescription().getDescription();
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
