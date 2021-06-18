/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Artist;
import com.adn.chinook.repository.ArtistRepository;
import static com.adn.chinook.utils.Paths.ARTIST;
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
@RequestMapping(REST_VERSION + ARTIST)
public class ArtistRestController implements BaseRestController<Artist, Integer> {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public String getEntity() {
        return ARTIST;
    }

    @Override
    public JpaRepository<Artist, Integer> getRepository() {
        return artistRepository;
    }

    @Operation(summary = "Ajout d'un nouveau artiste.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée à l'artiste trouvé."),
        @ApiResponse(responseCode = "302", description = "L'artiste existe déjà."),
        @ApiResponse(responseCode = "409", description = "L'artiste existe déjà."),
        @ApiResponse(responseCode = "404", description = "Artiste non trouvé.")
    })
    @Override
    public ResponseEntity<Artist> addObject(Artist object) {
        if (object.getArtistId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getArtistId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Artist artist = artistRepository.findByName(object.getName());
        if (artist != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        artist = getRepository().save(object);
        if (artist != null && artist.getArtistId() != null) {
            return new ResponseEntity<>(artist, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un artiste avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class))},
                description = "Artiste trouvé."),
        @ApiResponse(responseCode = "404", description = "Artiste non trouvé.")
    })
    @Override
    public ResponseEntity<Artist> getObject(Artist object) {
        Artist artist = getRepository().getOne(object.getArtistId());
        if (artist != null) {
            return new ResponseEntity<>(artist, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un artiste avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class))},
                description = "Artiste trouvé."),
        @ApiResponse(responseCode = "404", description = "Artiste non trouvé.")
    })
    @Override
    public ResponseEntity<Artist> getObject(Integer idObject) {
        Artist artist = getRepository().getOne(idObject);
        if (artist != null) {
            return new ResponseEntity<>(artist, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un artiste avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class))},
                description = "Artiste trouvé."),
        @ApiResponse(responseCode = "404", description = "Artiste non trouvé."),
        @ApiResponse(responseCode = "304", description = "Artiste non modifié.")
    })
    @Override
    public ResponseEntity<Artist> putObject(Artist object) {
        if (!getRepository().existsById(object.getArtistId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Artist artist = getRepository().save(object);
        if (artist != null) {
            return new ResponseEntity<>(artist, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un artiste.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Artiste supprimé."),
        @ApiResponse(responseCode = "404", description = "Artiste non trouvé.")
    })
    @Override
    public ResponseEntity<Artist> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un artiste.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Artiste supprimé."),
        @ApiResponse(responseCode = "404", description = "Artiste non trouvé.")
    })
    @Override
    public ResponseEntity<Artist> deleteObject(Artist object) {
        if (getRepository().existsById(object.getArtistId())) {
            getRepository().deleteById(object.getArtistId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Artist artist = artistRepository.findByName(object.getName());
        if (artist != null) {
            getRepository().delete(artist);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les artistes.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de artistes trouvés.")
    })
    @Override
    public ResponseEntity<List<Artist>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(
                Collections.singletonList(artistRepository.findByName(valeur)), HttpStatus.OK);
    }

    @Operation(summary = "Liste les artistes.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de artistes trouvés.")
    })
    @Override
    public ResponseEntity<List<Artist>> listObjects() {
        return new ResponseEntity<>(artistRepository.findAll(), HttpStatus.OK);
    }

}
