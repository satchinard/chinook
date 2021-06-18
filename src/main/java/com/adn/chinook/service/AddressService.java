/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Address;
import com.adn.chinook.repository.AddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressService(AddressRepository repository) {
        this.addressRepository = repository;
    }

    List<Address> findByAdresse(String adresse) {
        return this.addressRepository.findByAdresse(adresse);
    }

    List<Address> findByCity(String city) {
        return this.addressRepository.findByCity(city);
    }

    List<Address> findByCountry(String country) {
        return this.addressRepository.findByCountry(country);
    }

    List<Address> findByEmail(String email) {
        return this.addressRepository.findByEmail(email);
    }

    List<Address> findByPhone(String phone) {
        return this.addressRepository.findByPhone(phone);
    }

}
