package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Address;
import com.adn.chinook.form.AddressForm;
import com.adn.chinook.repository.AddressRepository;
import static com.adn.chinook.utils.Paths.ADDRESS;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cagecfi
 */
@Controller
@Slf4j
@RequestMapping(WEB_VERSION + ADDRESS)
public class AddressWebController
        implements BaseWebController<Address, Integer, AddressForm> {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public JpaRepository<Address, Integer> getRepository() {
        return addressRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + ADDRESS;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("adresses", addressRepository.findAll());
        mav.setViewName("address/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Address i, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("address/add");
            return mav;
        }
        if (i.getAdresse() != null && i.getEmail() != null) {
            addressRepository.save(i);
        }
        mav.addObject("adresses", addressRepository.findAll());
        mav.setViewName("address/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Address i, BindingResult bindingResult,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("adresses", addressRepository.findAll());
        mav.setViewName("address/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(@PathVariable("id") Integer pk) {
        Address address = null;
        try {
            address = addressRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (address != null && address.getAddressId() != null) {
            mav.addObject("address", address);
            mav.setViewName("address/get");
            return mav;
        }
        mav.setViewName("address/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Address address = addressRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (address != null) {
            mav.addObject("address", address);
            mav.setViewName("address/update");
            return mav;
        }
        mav.setViewName("address/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Address address = addressRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (address != null) {
            addressRepository.delete(address);
        }
        mav.addObject("adresses", addressRepository.findAll());
        mav.setViewName("address/all");
        return mav;
    }
}
