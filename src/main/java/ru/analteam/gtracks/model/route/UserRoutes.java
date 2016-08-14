package ru.analteam.gtracks.model.route;

import ru.analteam.gtracks.model.security.SecUser;

import javax.persistence.*;

/**
 * Created by dima-pc on 15.08.2016.
 */
@Entity
@Table(name = "user_routes")
public class UserRoutes {
    private Long id;
    private SecUser user;
    private Route route;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = SecUser.class)
    @JoinColumn(name = "user_id")
    public SecUser getUser() {
        return user;
    }

    public void setUser(SecUser user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = Route.class)
    @JoinColumn(name = "route_id")
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
