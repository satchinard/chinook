/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.MediaTypeChinook;
import com.adn.chinook.repository.MediaTypeRepository;
import static com.adn.chinook.utils.Paths.MEDIA_TYPE;
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
@RequestMapping(REST_VERSION + MEDIA_TYPE)
public class MediaTypeRestController implements BaseRestController<MediaTypeChinook, Integer> {

    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Override
    public String getEntity() {
        return MEDIA_TYPE;
    }

    @Override
    public JpaRepository<MediaTypeChinook, Integer> getRepository() {
        return mediaTypeRepository;
    }

    @Operation(summary = "Ajout d'un nouveau type de média.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MediaTypeChinook.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au type de média trouvé."),
        @ApiResponse(responseCode = "302", description = "Le type de média existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le type de média existe déjà."),
        @ApiResponse(responseCode = "404", description = "Type de média non trouvé.")
    })
    @Override
    public ResponseEntity<MediaTypeChinook> addObject(MediaTypeChinook object) {
        if (object.getMediaTypeId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getMediaTypeId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        MediaTypeChinook mediaType = mediaTypeRepository.findByName(object.getName());
        if (mediaType != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        mediaType = getRepository().save(object);
        if (mediaType != null && mediaType.getMediaTypeId() != null) {
            return new ResponseEntity<>(mediaType, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un type de média avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MediaTypeChinook.class))}, description = "Type de média trouvé."),
        @ApiResponse(responseCode = "404", description = "Type de média non trouvé.")
    })
    @Override
    public ResponseEntity<MediaTypeChinook> getObject(MediaTypeChinook object) {
        MediaTypeChinook mediaType = getRepository().getOne(object.getMediaTypeId());
        if (mediaType != null) {
            return new ResponseEntity<>(mediaType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un type de média avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MediaTypeChinook.class))}, description = "Type de média trouvé."),
        @ApiResponse(responseCode = "404", description = "Type de média non trouvé.")
    })
    @Override
    public ResponseEntity<MediaTypeChinook> getObject(Integer idObject) {
        MediaTypeChinook mediaType = getRepository().getOne(idObject);
        if (mediaType != null) {
            return new ResponseEntity<>(mediaType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un type de média avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MediaTypeChinook.class))}, description = "Type de média trouvé."),
        @ApiResponse(responseCode = "404", description = "Type de média non trouvé."),
        @ApiResponse(responseCode = "304", description = "Type de média non modifié.")
    })
    @Override
    public ResponseEntity<MediaTypeChinook> putObject(MediaTypeChinook object) {
        if (!getRepository().existsById(object.getMediaTypeId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MediaTypeChinook mediaType = getRepository().save(object);
        if (mediaType != null) {
            return new ResponseEntity<>(mediaType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un type de média.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Type de média supprimé."),
        @ApiResponse(responseCode = "404", description = "Type de média non trouvé.")
    })
    @Override
    public ResponseEntity<MediaTypeChinook> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un type de média.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Type de média supprimé."),
        @ApiResponse(responseCode = "404", description = "Type de média non trouvé.")
    })
    @Override
    public ResponseEntity<MediaTypeChinook> deleteObject(MediaTypeChinook object) {
        if (getRepository().existsById(object.getMediaTypeId())) {
            getRepository().deleteById(object.getMediaTypeId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        MediaTypeChinook mediaType = mediaTypeRepository.findByName(object.getName());
        if (mediaType != null) {
            getRepository().delete(mediaType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les type de médias.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de type de médias trouvés.")
    })
    @Override
    public ResponseEntity<List<MediaTypeChinook>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(
                Collections.singletonList(mediaTypeRepository.findByName(valeur)), HttpStatus.OK);
    }

    @Operation(summary = "Liste les type de médias.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de type de médias trouvés.")
    })
    @Override
    public ResponseEntity<List<MediaTypeChinook>> listObjects() {
        return new ResponseEntity<>(mediaTypeRepository.findAll(), HttpStatus.OK);
    }

}
