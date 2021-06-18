/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.web;

import static com.adn.chinook.utils.Paths.SEPARATOR;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author cagecfi
 */
@Controller
@Slf4j
public class ChinookController {

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String index() {
        return "/home.html";
    }

    @RequestMapping(path = "/ssl", method = RequestMethod.GET)
    public String ssl() {
        return "/lte/index_1.html";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login.html";
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String user() {
        return "/user.html";
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about() {
        return "/about.html";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String menu() {
        return "menu";
    }

    @RequestMapping(path = WEB_VERSION + SEPARATOR, method = RequestMethod.GET)
    public String menuPrincipal() {
        return "menu";
    }
}
