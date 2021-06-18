/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Privilege;
import com.adn.chinook.repository.PrivilegeRepository;
import static com.adn.chinook.utils.Paths.PRIVILEGE;
import static com.adn.chinook.utils.Paths.REST_VERSION;
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
@RequestMapping(REST_VERSION + PRIVILEGE)
public class PrivilegeRestController implements BaseRestController<Privilege, Long> {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public String getEntity() {
        return PRIVILEGE;
    }

    @Override
    public JpaRepository<Privilege, Long> getRepository() {
        return privilegeRepository;
    }

    @Operation(summary = "Ajout d'un nouveau privilège.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Privilege.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au privilège trouvé."),
        @ApiResponse(responseCode = "302", description = "Le privilège existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le privilège existe déjà."),
        @ApiResponse(responseCode = "404", description = "Privilege non trouvé.")
    })
    @Override
    public ResponseEntity<Privilege> addObject(Privilege object) {
        if (object.getPrivilegeId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getPrivilegeId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Privilege privilege = privilegeRepository.findByName(object.getName());
        if (privilege != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        privilege = getRepository().save(object);
        if (privilege != null && privilege.getPrivilegeId() != null) {
            return new ResponseEntity<>(privilege, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un privilège avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Privilege.class))},
                description = "Privilege trouvé."),
        @ApiResponse(responseCode = "404", description = "Privilege non trouvé.")
    })
    @Override
    public ResponseEntity<Privilege> getObject(Privilege object) {
        Privilege privilege = getRepository().getOne(object.getPrivilegeId());
        if (privilege != null) {
            return new ResponseEntity<>(privilege, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un privilège avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Privilege.class))},
                description = "Privilege trouvé."),
        @ApiResponse(responseCode = "404", description = "Privilege non trouvé.")
    })
    @Override
    public ResponseEntity<Privilege> getObject(Long idObject) {
        Privilege privilege = getRepository().getOne(idObject);
        if (privilege != null) {
            return new ResponseEntity<>(privilege, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un privilège avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Privilege.class))}, description = "Privilege trouvé."),
        @ApiResponse(responseCode = "404", description = "Privilege non trouvé."),
        @ApiResponse(responseCode = "304", description = "Privilege non modifié.")
    })
    @Override
    public ResponseEntity<Privilege> putObject(Privilege object) {
        if (!getRepository().existsById(object.getPrivilegeId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Privilege privilege = getRepository().save(object);
        if (privilege != null) {
            return new ResponseEntity<>(privilege, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un privilège.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Privilege supprimé."),
        @ApiResponse(responseCode = "404", description = "Privilege non trouvé.")
    })
    @Override
    public ResponseEntity<Privilege> deleteObject(Long idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un privilège.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Privilege supprimé."),
        @ApiResponse(responseCode = "404", description = "Privilege non trouvé.")
    })
    @Override
    public ResponseEntity<Privilege> deleteObject(Privilege object) {
        if (getRepository().existsById(object.getPrivilegeId())) {
            getRepository().deleteById(object.getPrivilegeId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Privilege privilege = privilegeRepository.findByName(object.getName());
        if (privilege != null) {
            getRepository().delete(privilege);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les privilèges.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))}, description = "Liste de privilèges trouvés.")
    })
    @Override
    public ResponseEntity<List<Privilege>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(
                Collections.singletonList(privilegeRepository.findByName(valeur)), HttpStatus.OK);
    }

    @Operation(summary = "Liste les privilèges.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de privilèges trouvés.")
    })
    @Override
    public ResponseEntity<List<Privilege>> listObjects() {
        return new ResponseEntity<>(privilegeRepository.findAll(), HttpStatus.OK);
    }

}
