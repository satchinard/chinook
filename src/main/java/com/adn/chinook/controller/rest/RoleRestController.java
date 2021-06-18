package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Role;
import com.adn.chinook.repository.RoleRepository;
import static com.adn.chinook.utils.Paths.REST_VERSION;
import static com.adn.chinook.utils.Paths.ROLE;
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
@RequestMapping(REST_VERSION + ROLE)
public class RoleRestController implements BaseRestController<Role, Long> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String getEntity() {
        return ROLE;
    }

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Operation(summary = "Ajout d'un nouveau role.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Role.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au role trouvé."),
        @ApiResponse(responseCode = "302", description = "Le role existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le role existe déjà."),
        @ApiResponse(responseCode = "404", description = "Role non trouvé.")
    })
    @Override
    public ResponseEntity<Role> addObject(Role object) {
        if (object.getRoleId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getRoleId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Role role = roleRepository.findByName(object.getName());
        if (role != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        role = getRepository().save(object);
        if (role != null && role.getRoleId() != null) {
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un role avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Role.class))}, description = "Role trouvé."),
        @ApiResponse(responseCode = "404", description = "Role non trouvé.")
    })
    @Override
    public ResponseEntity<Role> getObject(Role object) {
        Role role = getRepository().getOne(object.getRoleId());
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un role avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Role.class))}, description = "Role trouvé."),
        @ApiResponse(responseCode = "404", description = "Role non trouvé.")
    })
    @Override
    public ResponseEntity<Role> getObject(Long idObject) {
        Role role = getRepository().getOne(idObject);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un role avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Role.class))}, description = "Role trouvé."),
        @ApiResponse(responseCode = "404", description = "Role non trouvé."),
        @ApiResponse(responseCode = "304", description = "Role non modifié.")
    })
    @Override
    public ResponseEntity<Role> putObject(Role object) {
        if (!getRepository().existsById(object.getRoleId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Role role = getRepository().save(object);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un role.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Role supprimé."),
        @ApiResponse(responseCode = "404", description = "Role non trouvé.")
    })
    @Override
    public ResponseEntity<Role> deleteObject(Long idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un role.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Role supprimé."),
        @ApiResponse(responseCode = "404", description = "Role non trouvé.")
    })
    @Override
    public ResponseEntity<Role> deleteObject(Role object) {
        if (getRepository().existsById(object.getRoleId())) {
            getRepository().deleteById(object.getRoleId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Role role = roleRepository.findByName(object.getName());
        if (role != null) {
            getRepository().delete(role);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les roles.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de roles trouvés.")
    })
    @Override
    public ResponseEntity<List<Role>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(
                Collections.singletonList(roleRepository.findByName(valeur)), HttpStatus.OK);
    }

    @Operation(summary = "Liste les roles.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de roles trouvés.")
    })
    @Override
    public ResponseEntity<List<Role>> listObjects() {
        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }

}
