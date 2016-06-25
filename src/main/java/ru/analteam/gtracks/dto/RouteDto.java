package ru.analteam.gtracks.dto;

import java.util.List;

/**
 * Created by dima-pc on 07.06.2016.
 */
public class RouteDto {

    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private UserDto user;

    private List<RoutePointDto> route;


    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<RoutePointDto> getRoute() {
        return route;
    }

    public void setRoute(List<RoutePointDto> route) {
        this.route = route;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
