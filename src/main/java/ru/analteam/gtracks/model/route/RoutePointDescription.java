package ru.analteam.gtracks.model.route;

import javax.persistence.*;

/**
 * Created by dima-pc on 18.12.2016.
 */
@Entity
@Table(name = "route_point_description")
public class RoutePointDescription {

    private Long id;

    private String shortDescription;

    private String description;

    private RoutePoint routePoint;

    public RoutePointDescription() {
    }

    public RoutePointDescription(String shortDescription,
                                 String description,
                                 RoutePoint routePoint) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.routePoint = routePoint;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name="route_point_id")
    public RoutePoint getRoutePoint() {
        return routePoint;
    }

    public void setRoutePoint(RoutePoint routePoint) {
        this.routePoint = routePoint;
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

}
