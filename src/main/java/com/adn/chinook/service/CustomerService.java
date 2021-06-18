/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Customer;
import com.adn.chinook.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    List<Customer> findByFirstName(String firstName) {
        return this.customerRepository.findByFirstName(firstName);
    }

    List<Customer> findByLastName(String lastName) {
        return this.customerRepository.findByLastName(lastName);
    }

    List<Customer> findByCompany(String company) {
        return this.customerRepository.findByCompany(company);
    }

    Customer findByFirstNameAndLastName(String firstName, String lastName) {
        return this.customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}
