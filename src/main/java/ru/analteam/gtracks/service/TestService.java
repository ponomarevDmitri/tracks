package ru.analteam.gtracks.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * Created by dima-pc on 09.12.2015.
 */
@Service
public class TestService {

    @PostConstruct
    public void init(){
//        System.out.println("HELLO WORLD FROM TEST_SERVICE!!");
    }
}
