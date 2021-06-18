/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Customer;
import com.adn.chinook.repository.CustomerRepository;
import static com.adn.chinook.utils.Paths.CUSTOMER;
import static com.adn.chinook.utils.Paths.REST_VERSION;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cagecfi
 */
@RestController
@Slf4j
@RequestMapping(REST_VERSION + CUSTOMER)
public class CustomerRestController implements BaseRestController<Customer, Integer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String getEntity() {
        return CUSTOMER;
    }

    @Override
    public JpaRepository<Customer, Integer> getRepository() {
        return customerRepository;
    }

    @Operation(summary = "Ajout d'un nouveau client.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au client trouvé."),
        @ApiResponse(responseCode = "302", description = "Le client existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le client existe déjà."),
        @ApiResponse(responseCode = "404", description = "Client non trouvé.")
    })
    @Override
    public ResponseEntity<Customer> addObject(Customer object) {
        if (object.getCustomerId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getCustomerId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Customer customer = getRepository().save(object);
        if (customer != null && customer.getCustomerId() != null) {
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un client avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class))},
                description = "Client trouvé."),
        @ApiResponse(responseCode = "404", description = "Client non trouvé.")
    })
    @Override
    public ResponseEntity<Customer> getObject(Customer object) {
        Customer customer = getRepository().getOne(object.getCustomerId());
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un client avec son id.",
            method = "GET"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Client trouvé."),
        @ApiResponse(responseCode = "404", description = "Client non trouvé.")
    })
    @Override
    public ResponseEntity<Customer> getObject(Integer idObject) {
        Customer customer = getRepository().getOne(idObject);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un client avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Client trouvé."),
        @ApiResponse(responseCode = "404", description = "Client non trouvé."),
        @ApiResponse(responseCode = "304", description = "Client non modifié.")
    })
    @Override
    public ResponseEntity<Customer> putObject(Customer object) {
        if (!getRepository().existsById(object.getCustomerId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = getRepository().save(object);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un client.",
            method = "GET"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Client supprimé."),
        @ApiResponse(responseCode = "404", description = "Client non trouvé.")
    })
    @Override
    public ResponseEntity<Customer> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un client.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Client supprimé."),
        @ApiResponse(responseCode = "404", description = "Client non trouvé.")
    })
    @Override
    public ResponseEntity<Customer> deleteObject(Customer object) {
        if (getRepository().existsById(object.getCustomerId())) {
            getRepository().deleteById(object.getCustomerId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les clients.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de clients trouvés.")
    })
    @Override
    public ResponseEntity<List<Customer>> listObjects(String attribut, String valeur) {
        switch (attribut) {
            case "firstName":
                return new ResponseEntity<>(customerRepository.findByFirstName(valeur), HttpStatus.OK);
            case "lastName":
                return new ResponseEntity<>(customerRepository.findByFirstName(valeur), HttpStatus.OK);
            case "company":
                return new ResponseEntity<>(customerRepository.findByFirstName(valeur), HttpStatus.OK);
            default:
                return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Liste les clients.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))}, description = "Liste de clients trouvés.")
    })
    @Override
    public ResponseEntity<List<Customer>> listObjects() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

}
