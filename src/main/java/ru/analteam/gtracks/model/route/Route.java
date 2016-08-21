package ru.analteam.gtracks.model.route;

import ru.analteam.gtracks.model.security.SecUser;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dima-pc on 04.06.2016.
 */
@Entity
@Table(name = "route")
public class Route {

    private Long id;

    private List<RoutePoint> routePoints;

    private String name;
    private String shortDescription;
    private String description;
    
    private SecUser user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "short_description")
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

    @ManyToOne
    @JoinTable(name="user_routes",
            joinColumns = @JoinColumn(name="route_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    public SecUser getUser() {
        return user;
    }

    public void setUser(SecUser user) {
        this.user = user;
    }
}
