/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

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
public class ChinookRestController {

    @RequestMapping(method = RequestMethod.GET, path = "/api")
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }
}
