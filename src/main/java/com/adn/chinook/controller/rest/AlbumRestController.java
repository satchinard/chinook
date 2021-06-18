/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Album;
import com.adn.chinook.repository.AlbumRepository;
import static com.adn.chinook.utils.Paths.ALBUM;
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
@RequestMapping(REST_VERSION + ALBUM)
public class AlbumRestController implements BaseRestController<Album, Integer> {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public String getEntity() {
        return ALBUM;
    }

    @Override
    public JpaRepository<Album, Integer> getRepository() {
        return albumRepository;
    }

    @Operation(summary = "Ajout d'un nouveau album.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Album.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au album trouvé."),
        @ApiResponse(responseCode = "302", description = "L'album existe déjà."),
        @ApiResponse(responseCode = "409", description = "L'album existe déjà."),
        @ApiResponse(responseCode = "404", description = "Album non trouvé.")
    })
    @Override
    public ResponseEntity<Album> addObject(Album object) {
        if (object.getAlbumId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getAlbumId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Album album = getRepository().save(object);
        if (album != null && album.getAlbumId() != null) {
            return new ResponseEntity<>(album, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un album avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Album.class))}, description = "Album trouvé."),
        @ApiResponse(responseCode = "404", description = "Album non trouvé.")
    })
    @Override
    public ResponseEntity<Album> getObject(Album object) {
        Album album = getRepository().getOne(object.getAlbumId());
        if (album != null) {
            return new ResponseEntity<>(album, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un album avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Album.class))}, description = "Album trouvé."),
        @ApiResponse(responseCode = "404", description = "Album non trouvé.")
    })
    @Override
    public ResponseEntity<Album> getObject(Integer idObject) {
        Album album = getRepository().getOne(idObject);
        if (album != null) {
            return new ResponseEntity<>(album, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un album avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Album.class))}, description = "Album trouvé."),
        @ApiResponse(responseCode = "404", description = "Album non trouvé."),
        @ApiResponse(responseCode = "304", description = "Album non modifié.")
    })
    @Override
    public ResponseEntity<Album> putObject(Album object) {
        if (!getRepository().existsById(object.getAlbumId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Album album = getRepository().save(object);
        if (album != null) {
            return new ResponseEntity<>(album, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un album.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Album supprimé."),
        @ApiResponse(responseCode = "404", description = "Album non trouvé.")
    })
    @Override
    public ResponseEntity<Album> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un album.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Album supprimé."),
        @ApiResponse(responseCode = "404", description = "Album non trouvé.")
    })
    @Override
    public ResponseEntity<Album> deleteObject(Album object) {
        if (getRepository().existsById(object.getAlbumId())) {
            getRepository().deleteById(object.getAlbumId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les albums.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de albums trouvés.")
    })
    @Override
    public ResponseEntity<List<Album>> listObjects(String attribut, String valeur) {
        if ("title".equals(attribut)) {
            return new ResponseEntity<>(
                    Collections.singletonList(albumRepository.findByTitle(valeur)), HttpStatus.OK);
        }
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Liste les albums.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste des albums trouvés.")
    })
    @Override
    public ResponseEntity<List<Album>> listObjects() {
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.OK);
    }

}
