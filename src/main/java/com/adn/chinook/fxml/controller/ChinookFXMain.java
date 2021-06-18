/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.fxml.controller;

import com.adn.chinook.service.AddressService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author CAGECFI
 */
@Component
@FxmlView("main-stage.fxml")
public class ChinookFXMain {

    private AddressService addressService;
    @FXML
    private Label weatherLabel;

    @Autowired
    public ChinookFXMain(AddressService addressService) {
        this.addressService = addressService;
    }

    public void loadWeatherForecast(ActionEvent actionEvent) {
        this.weatherLabel.setText(addressService.getClass().getName());
    }
}
