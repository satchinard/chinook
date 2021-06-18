package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.User;
import com.adn.chinook.form.UserForm;
import com.adn.chinook.repository.RoleRepository;
import com.adn.chinook.repository.UserRepository;
import static com.adn.chinook.utils.Paths.USER;
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
@RequestMapping(WEB_VERSION + USER)
public class UserWebController implements BaseWebController<User, Long, UserForm> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + USER;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(User i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("allRoles", roleRepository.findAll());
            mav.setViewName("user/add");
            return mav;
        }
        if (i.getFirstName() != null) {
            userRepository.save(i);
        }
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Long pk) {
        User user = null;
        try {
            user = userRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (user != null && user.getUserId() != null) {
            mav.addObject("allRoles", roleRepository.findAll());
            mav.addObject("user", user);
            mav.setViewName("user/get");
            return mav;
        }
        mav.setViewName("user/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Long pk) {
        User user = userRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (user != null) {
            mav.addObject("allRoles", roleRepository.findAll());
            mav.addObject("user", user);
            mav.setViewName("user/update");
            return mav;
        }
        mav.setViewName("user/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Long pk) {
        User user = userRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (user != null) {
            try {
                userRepository.delete(user);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(User i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/all");
        return mav;
    }

}
