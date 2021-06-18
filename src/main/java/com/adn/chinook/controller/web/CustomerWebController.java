package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Customer;
import com.adn.chinook.form.CustomerForm;
import com.adn.chinook.repository.AddressRepository;
import com.adn.chinook.repository.CustomerRepository;
import static com.adn.chinook.utils.Paths.CUSTOMER;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping(WEB_VERSION + CUSTOMER)
public class CustomerWebController implements BaseWebController<Customer, Integer, CustomerForm> {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public JpaRepository<Customer, Integer> getRepository() {
        return customerRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + CUSTOMER;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("customers", customerRepository.findAll());
        mav.setViewName("customer/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(@Valid Customer customer,
            BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("customer/add");
            return mav;
        }
        if (customer.getFirstName() != null) {
            customerRepository.save(customer);
        }
        mav.addObject("customers", customerRepository.findAll());
        mav.setViewName("customer/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        Customer customer = null;
        try {
            customer = customerRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (customer != null && customer.getCustomerId() != null) {
            mav.addObject("customer", customer);
            mav.setViewName("customer/get");
            return mav;
        }
        mav.setViewName("customer/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Customer customer = customerRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (customer != null) {
            mav.addObject("customer", customer);
            mav.setViewName("customer/update");
            return mav;
        }
        mav.setViewName("customer/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Customer customer = customerRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (customer != null) {
            try {
                customerRepository.delete(customer);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("customers", customerRepository.findAll());
        mav.setViewName("customer/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Customer i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("customers", customerRepository.findAll());
        mav.setViewName("customer/all");
        return mav;
    }

}
