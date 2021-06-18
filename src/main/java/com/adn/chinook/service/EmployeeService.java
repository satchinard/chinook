/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Artist;
import com.adn.chinook.entity.Employee;
import com.adn.chinook.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    List<Employee> findByFirstName(String firstName) {
        return this.employeeRepository.findByFirstName(firstName);
    }

    List<Employee> findByLastName(String lastName) {
        return this.employeeRepository.findByLastName(lastName);
    }

    List<Employee> findByTitle(String title) {
        return this.employeeRepository.findByTitle(title);
    }

    List<Employee> findByArtist(Artist artist) {
        return this.employeeRepository.findByArtist(artist);
    }

    Employee findByFirstNameAndLastName(String firstName, String lastName) {
        return this.employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}
