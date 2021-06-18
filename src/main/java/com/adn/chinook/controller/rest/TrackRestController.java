package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Track;
import com.adn.chinook.repository.TrackRepository;
import static com.adn.chinook.utils.Paths.REST_VERSION;
import static com.adn.chinook.utils.Paths.TRACK;
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
@RequestMapping(REST_VERSION + TRACK)
public class TrackRestController implements BaseRestController<Track, Integer> {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public String getEntity() {
        return TRACK;
    }

    @Override
    public JpaRepository<Track, Integer> getRepository() {
        return trackRepository;
    }

    @Operation(summary = "Ajout d'un nouveau track.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Track.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au track trouvé."),
        @ApiResponse(responseCode = "302", description = "Le track existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le track existe déjà."),
        @ApiResponse(responseCode = "404", description = "Track non trouvé.")
    })
    @Override
    public ResponseEntity<Track> addObject(Track object) {
        if (object.getTrackId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getTrackId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Track track = getRepository().save(object);
        if (track != null && track.getTrackId() != null) {
            return new ResponseEntity<>(track, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un track avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Track.class))}, description = "Track trouvé."),
        @ApiResponse(responseCode = "404", description = "Track non trouvé.")
    })
    @Override
    public ResponseEntity<Track> getObject(Track object) {
        Track track = getRepository().getOne(object.getTrackId());
        if (track != null) {
            return new ResponseEntity<>(track, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un track avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Track.class))}, description = "Track trouvé."),
        @ApiResponse(responseCode = "404", description = "Track non trouvé.")
    })
    @Override
    public ResponseEntity<Track> getObject(Integer idObject) {
        Track track = getRepository().getOne(idObject);
        if (track != null) {
            return new ResponseEntity<>(track, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un track avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Track.class))}, description = "Track trouvé."),
        @ApiResponse(responseCode = "404", description = "Track non trouvé."),
        @ApiResponse(responseCode = "304", description = "Track non modifié.")
    })
    @Override
    public ResponseEntity<Track> putObject(Track object) {
        if (!getRepository().existsById(object.getTrackId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Track track = getRepository().save(object);
        if (track != null) {
            return new ResponseEntity<>(track, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un track.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Track supprimé."),
        @ApiResponse(responseCode = "404", description = "Track non trouvé.")
    })
    @Override
    public ResponseEntity<Track> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un track.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Track supprimé."),
        @ApiResponse(responseCode = "404", description = "Track non trouvé.")
    })
    @Override
    public ResponseEntity<Track> deleteObject(Track object) {
        if (getRepository().existsById(object.getTrackId())) {
            getRepository().deleteById(object.getTrackId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les tracks.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de tracks trouvés.")
    })
    @Override
    public ResponseEntity<List<Track>> listObjects(String attribut, String valeur) {
        switch (attribut) {
            case "name":
                return new ResponseEntity<>(trackRepository.findByName(valeur), HttpStatus.OK);
            case "composer":
                return new ResponseEntity<>(trackRepository.findByComposer(valeur), HttpStatus.OK);
            default:
                return new ResponseEntity<>(trackRepository.findAll(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Liste les tracks.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de tracks trouvés.")
    })
    @Override
    public ResponseEntity<List<Track>> listObjects() {
        return new ResponseEntity<>(trackRepository.findAll(), HttpStatus.OK);
    }

}
