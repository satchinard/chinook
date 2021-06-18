package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Employee;
import com.adn.chinook.form.EmployeeForm;
import com.adn.chinook.repository.AddressRepository;
import com.adn.chinook.repository.ArtistRepository;
import com.adn.chinook.repository.EmployeeRepository;
import static com.adn.chinook.utils.Paths.EMPLOYE;
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
@RequestMapping(WEB_VERSION + EMPLOYE)
public class EmployeeWebController implements BaseWebController<Employee, Integer, EmployeeForm> {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public JpaRepository<Employee, Integer> getRepository() {
        return employeeRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + EMPLOYE;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("employees", employeeRepository.findAll());
        mav.setViewName("employee/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Employee i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("allArtists", artistRepository.findAll());
            mav.setViewName("employee/add");
            return mav;
        }
        if (i.getFirstName() != null) {
            employeeRepository.save(i);
        }
        mav.addObject("employees", employeeRepository.findAll());
        mav.setViewName("employee/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        Employee employee = null;
        try {
            employee = employeeRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (employee != null && employee.getEmployeeId() != null) {
            mav.addObject("allArtists", artistRepository.findAll());
            mav.addObject("employee", employee);
            mav.setViewName("employee/get");
            return mav;
        }
        mav.setViewName("employee/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Employee employee = employeeRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (employee != null) {
            mav.addObject("allArtists", artistRepository.findAll());
            mav.addObject("employee", employee);
            mav.setViewName("employee/update");
            return mav;
        }
        mav.setViewName("employee/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Employee employee = employeeRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (employee != null) {
            try {
                employeeRepository.delete(employee);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("employees", employeeRepository.findAll());
        mav.setViewName("employee/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Employee i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("employees", employeeRepository.findAll());
        mav.setViewName("employee/all");
        return mav;
    }

}
