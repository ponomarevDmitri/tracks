package ru.analteam.gtracks.controller.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dima-pc on 16.08.2016.
 */
@Controller
@RequestMapping("/map")
public class MapController {

    @RequestMapping("/simpleMap")
    public String createRoute(){
        return "map/simpleMap";
    }
}
