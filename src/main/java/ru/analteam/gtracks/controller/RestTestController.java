package ru.analteam.gtracks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dima-pc on 06.02.2016.
 */
@RestController
@RequestMapping("test")
public class RestTestController {

    @RequestMapping("rest")
    public String doRestTest(){
        return "Hello world";
    }
}
