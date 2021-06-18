/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author cagecfi
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification<I> {

    private String icone;
    private String status;
    private String message = "Succès !";
    private I body;
}
