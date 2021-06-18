package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Privilege;
import com.adn.chinook.form.PrivilegeForm;
import com.adn.chinook.repository.PrivilegeRepository;
import com.adn.chinook.repository.RoleRepository;
import static com.adn.chinook.utils.Paths.PRIVILEGE;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(WEB_VERSION + PRIVILEGE)
public class PrivilegeWebController implements BaseWebController<Privilege, Long, PrivilegeForm> {

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public JpaRepository<Privilege, Long> getRepository() {
        return privilegeRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + PRIVILEGE;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("privileges", privilegeRepository.findAll());
        mav.setViewName("privilege/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Privilege i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("privilege/add");
            return mav;
        }
        if (i.getName() != null) {
            privilegeRepository.save(i);
        }
        mav.addObject("privileges", privilegeRepository.findAll());
        mav.setViewName("privilege/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Long pk) {
        Privilege privilege = null;
        try {
            privilege = privilegeRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (privilege != null && privilege.getPrivilegeId() != null) {
            mav.addObject("privilege", privilege);
            mav.setViewName("privilege/get");
            return mav;
        }
        mav.setViewName("privilege/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Long pk) {
        Privilege privilege = privilegeRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (privilege != null) {
            mav.addObject("privilege", privilege);
            mav.setViewName("privilege/update");
            return mav;
        }
        mav.setViewName("privilege/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Long pk) {
        Privilege privilege = privilegeRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (privilege != null) {
            try {
                privilegeRepository.delete(privilege);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("privileges", privilegeRepository.findAll());
        mav.setViewName("privilege/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Privilege i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("privileges", privilegeRepository.findAll());
        mav.setViewName("privilege/all");
        return mav;
    }

}
