/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.PlayList;
import com.adn.chinook.repository.PlayListRepository;
import static com.adn.chinook.utils.Paths.PLAY_LIST;
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
@RequestMapping(REST_VERSION + PLAY_LIST)
public class PlayListRestController implements BaseRestController<PlayList, Integer> {

    @Autowired
    private PlayListRepository playListRepository;

    @Override
    public String getEntity() {
        return PLAY_LIST;
    }

    @Override
    public JpaRepository<PlayList, Integer> getRepository() {
        return playListRepository;
    }

    @Operation(summary = "Ajout d'un nouveau playList.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayList.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au playList trouvé."),
        @ApiResponse(responseCode = "302", description = "Le playList existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le playList existe déjà."),
        @ApiResponse(responseCode = "404", description = "PlayList non trouvé.")
    })
    @Override
    public ResponseEntity<PlayList> addObject(PlayList object) {
        if (object.getPlayListId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getPlayListId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        PlayList playList = playListRepository.findByName(object.getName());
        if (playList != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        playList = getRepository().save(object);
        if (playList != null && playList.getPlayListId() != null) {
            return new ResponseEntity<>(playList, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un playList avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayList.class))}, description = "PlayList trouvé."),
        @ApiResponse(responseCode = "404", description = "PlayList non trouvé.")
    })
    @Override
    public ResponseEntity<PlayList> getObject(PlayList object) {
        PlayList playList = getRepository().getOne(object.getPlayListId());
        if (playList != null) {
            return new ResponseEntity<>(playList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un playList avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayList.class))}, description = "PlayList trouvé."),
        @ApiResponse(responseCode = "404", description = "PlayList non trouvé.")
    })
    @Override
    public ResponseEntity<PlayList> getObject(Integer idObject) {
        PlayList playList = getRepository().getOne(idObject);
        if (playList != null) {
            return new ResponseEntity<>(playList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un playList avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayList.class))}, description = "PlayList trouvé."),
        @ApiResponse(responseCode = "404", description = "PlayList non trouvé."),
        @ApiResponse(responseCode = "304", description = "PlayList non modifié.")
    })
    @Override
    public ResponseEntity<PlayList> putObject(PlayList object) {
        if (!getRepository().existsById(object.getPlayListId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PlayList playList = getRepository().save(object);
        if (playList != null) {
            return new ResponseEntity<>(playList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un playList.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "PlayList supprimé."),
        @ApiResponse(responseCode = "404", description = "PlayList non trouvé.")
    })
    @Override
    public ResponseEntity<PlayList> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un playList.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "PlayList supprimé."),
        @ApiResponse(responseCode = "404", description = "PlayList non trouvé.")
    })
    @Override
    public ResponseEntity<PlayList> deleteObject(PlayList object) {
        if (getRepository().existsById(object.getPlayListId())) {
            getRepository().deleteById(object.getPlayListId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PlayList playList = playListRepository.findByName(object.getName());
        if (playList != null) {
            getRepository().delete(playList);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les playLists.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de playLists trouvés.")
    })
    @Override
    public ResponseEntity<List<PlayList>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(
                Collections.singletonList(playListRepository.findByName(valeur)), HttpStatus.OK);
    }

    @Operation(summary = "Liste les playLists.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de playLists trouvés.")
    })
    @Override
    public ResponseEntity<List<PlayList>> listObjects() {
        return new ResponseEntity<>(playListRepository.findAll(), HttpStatus.OK);
    }

}
