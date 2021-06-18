/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.base;

import static com.adn.chinook.utils.Paths.*;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @param <I>
 * @param <PK>
 * @author cagecfi
 * @param <Z>
 */
public interface BaseWebController<I, PK extends Serializable, Z> {

    /**
     * @return Le repository par défaut.
     */
    JpaRepository<I, PK> getRepository();

    /**
     *
     * @return Le lien par défaut du controlleur.
     */
    String getWebControllerPath();

    @RequestMapping(value = CHINOOK_DEFAULT_PATH, method = {RequestMethod.GET, RequestMethod.POST})
    ModelAndView defaultPath();

    @RequestMapping(value = CHINOOK_ADD, method = {RequestMethod.POST, RequestMethod.GET})
    ModelAndView addObject(@Valid I i,
            BindingResult bindingResult, HttpServletRequest request);

//    @RequestMapping(value = CHINOOK_GET, method = {RequestMethod.POST, RequestMethod.GET})
//    ModelAndView getObject(@Valid I i,
//            BindingResult bindingResult, HttpServletRequest request);
//
//    @RequestMapping(value = CHINOOK_UPDATE, method = {RequestMethod.POST, RequestMethod.GET})
//    ModelAndView updateObject(@Valid I i,
//            BindingResult bindingResult, HttpServletRequest request);
//
//    @RequestMapping(value = CHINOOK_DELETE, method = {RequestMethod.POST, RequestMethod.GET})
//    ModelAndView deleteObject(@Valid I i,
//            BindingResult bindingResult, HttpServletRequest request);
    @RequestMapping(value = CHINOOK_GET + "/{id}", method = {RequestMethod.GET})
    ModelAndView getObject(@PathVariable("id") PK pk);

    @RequestMapping(value = CHINOOK_UPDATE + "/{id}", method = {RequestMethod.GET})
    ModelAndView updateObject(@PathVariable("id") PK pk);

    @RequestMapping(value = CHINOOK_DELETE + "/{id}", method = {RequestMethod.GET})
    ModelAndView deleteObject(@PathVariable("id") PK pk);

    @RequestMapping(value = CHINOOK_ALL, method = {RequestMethod.POST, RequestMethod.GET})
    ModelAndView listObjects(@Valid I i,
            BindingResult bindingResult, HttpServletRequest request);
//    @PostMapping(CHINOOK_ADD)
//    I add(@RequestBody Z form);
//
//    @PostMapping(CHINOOK_UPDATE)
//    I update(@RequestBody Z form);
//
//    @GetMapping(CHINOOK_GET)
//    I select(@RequestBody Z form);
//
//    @PostMapping(CHINOOK_DELETE)
//    void delete(@RequestBody Z form);
//
//    @PostMapping(CHINOOK_ALL)
//    List<I> list(@RequestBody Z form);
}
