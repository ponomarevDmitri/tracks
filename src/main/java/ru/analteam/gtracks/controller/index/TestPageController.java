package ru.analteam.gtracks.controller.index;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dima-pc on 22.10.2016.
 */
@RequestMapping("*")
public class TestPageController {
    @RequestMapping
    public String test(){
        return "wellcome_page";
    }
}
