/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.User;
import com.adn.chinook.repository.UserRepository;
import static com.adn.chinook.utils.Paths.REST_VERSION;
import static com.adn.chinook.utils.Paths.USER;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collections;
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
@RequestMapping(REST_VERSION + USER)
public class UserRestController implements BaseRestController<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String getEntity() {
        return USER;
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Operation(summary = "Ajout d'un nouveau user.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au user trouvé."),
        @ApiResponse(responseCode = "302", description = "Le user existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le user existe déjà."),
        @ApiResponse(responseCode = "404", description = "User non trouvé.")
    })
    @Override
    public ResponseEntity<User> addObject(User object) {
        if (object.getUserId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getUserId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User user = getRepository().save(object);
        if (user != null && user.getUserId() != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un user avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))}, description = "User trouvé."),
        @ApiResponse(responseCode = "404", description = "User non trouvé.")
    })
    @Override
    public ResponseEntity<User> getObject(User object) {
        User user = getRepository().getOne(object.getUserId());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un user avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))},
                description = "User trouvé."),
        @ApiResponse(responseCode = "404", description = "User non trouvé.")
    })
    @Override
    public ResponseEntity<User> getObject(Long idObject) {
        User user = getRepository().getOne(idObject);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un user avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))},
                description = "User trouvé."),
        @ApiResponse(responseCode = "404", description = "User non trouvé."),
        @ApiResponse(responseCode = "304", description = "User non modifié.")
    })
    @Override
    public ResponseEntity<User> putObject(User object) {
        if (!getRepository().existsById(object.getUserId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = getRepository().save(object);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un user.", method = "GET"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User supprimé."),
        @ApiResponse(responseCode = "404", description = "User non trouvé.")
    })
    @Override
    public ResponseEntity<User> deleteObject(Long idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un user.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User supprimé."),
        @ApiResponse(responseCode = "404", description = "User non trouvé.")
    })
    @Override
    public ResponseEntity<User> deleteObject(User object) {
        if (getRepository().existsById(object.getUserId())) {
            getRepository().deleteById(object.getUserId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les users.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de users trouvés.")
    })
    @Override
    public ResponseEntity<List<User>> listObjects(String attribut, String valeur) {
        switch (attribut) {
            case "userName":
                return new ResponseEntity<>(Collections.singletonList(userRepository.findByUsername(valeur)), HttpStatus.OK);
            case "email":
                return new ResponseEntity<>(Collections.singletonList(userRepository.findByEmail(valeur)), HttpStatus.OK);
            default:
                return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Liste les users.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de users trouvés.")
    })
    @Override
    public ResponseEntity<List<User>> listObjects() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

}
