/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.test;

import java.io.Serializable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author CAGECFI
 */
@ExtendWith(MockitoExtension.class)
public interface BaseRestControllerTest<I, PK extends Serializable> {

    @Test
    void givenObject_ReturnObjectAdded();

    @Test
    void givenObject_ReturnObjectFound();

    @Test
    void givenObjectId_ReturnObjectFound();

    @Test
    void givenObject_ReturnObjectUpdated();

    @Test
    void givenObject_DeleteObject();

    @Test
    void givenObjectId_DeleteObject();

    @Test
    void givenAttribute_ReturnSelectedObjectList();

    @Test
    void returnObjectsList();

}
