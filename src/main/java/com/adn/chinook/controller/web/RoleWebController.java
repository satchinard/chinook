package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Role;
import com.adn.chinook.form.RoleForm;
import com.adn.chinook.repository.PrivilegeRepository;
import com.adn.chinook.repository.RoleRepository;
import com.adn.chinook.repository.UserRepository;
import static com.adn.chinook.utils.Paths.ROLE;
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
@RequestMapping(WEB_VERSION + ROLE)
public class RoleWebController implements BaseWebController<Role, Long, RoleForm> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + ROLE;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles", roleRepository.findAll());
        mav.setViewName("role/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Role i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("role/add");
            return mav;
        }
        if (i.getName() != null) {
            roleRepository.save(i);
        }
        mav.addObject("roles", roleRepository.findAll());
        mav.setViewName("role/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Long pk) {
        Role role = null;
        try {
            role = roleRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (role != null && role.getRoleId() != null) {
            mav.addObject("role", role);
            mav.setViewName("role/get");
            return mav;
        }
        mav.setViewName("role/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Long pk) {
        Role role = roleRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (role != null) {
            mav.addObject("role", role);
            mav.setViewName("role/update");
            return mav;
        }
        mav.setViewName("role/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Long pk) {
        Role role = roleRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (role != null) {
            try {
                roleRepository.delete(role);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("roles", roleRepository.findAll());
        mav.setViewName("role/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Role i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles", roleRepository.findAll());
        mav.setViewName("role/all");
        return mav;
    }

}
