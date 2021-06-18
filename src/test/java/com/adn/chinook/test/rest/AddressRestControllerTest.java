/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.test.rest;

import com.adn.chinook.repository.AddressRepository;
import com.adn.chinook.test.BaseRestControllerTest;
import javax.mail.Address;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author CAGECFI
 */
public class AddressRestControllerTest implements BaseRestControllerTest<Address, Long> {

    @Autowired
    AddressRepository repository;

    @Override
    public void givenObject_ReturnObjectAdded() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void givenObject_ReturnObjectFound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void givenObjectId_ReturnObjectFound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void givenObject_ReturnObjectUpdated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void givenObject_DeleteObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void givenObjectId_DeleteObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void givenAttribute_ReturnSelectedObjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void returnObjectsList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
