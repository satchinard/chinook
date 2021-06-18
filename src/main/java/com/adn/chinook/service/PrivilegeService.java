/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Privilege;
import com.adn.chinook.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository playListRepository) {
        this.privilegeRepository = playListRepository;
    }

    Privilege findByName(String name) {
        return this.privilegeRepository.findByName(name);
    }

}
