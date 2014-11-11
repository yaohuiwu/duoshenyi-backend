package com.duoshenyi.backend.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuyaohui on 14-11-11.
 */
@RestController
@RequestMapping(produces = {"application/json"})
public class GreetingController {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping(value = {"/greeting"},method = {RequestMethod.GET})
    public String greet(@RequestParam String username){

        LOG.debug("Greeting to user:{}.",username);
        return "Hello," + username + " !";
    }
}
