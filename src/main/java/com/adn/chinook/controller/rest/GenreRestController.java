package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Genre;
import com.adn.chinook.repository.GenreRepository;
import static com.adn.chinook.utils.Paths.GENRE;
import static com.adn.chinook.utils.Paths.REST_VERSION;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cagecfi
 */
@RestController
@Slf4j
@RequestMapping(REST_VERSION + GENRE)
public class GenreRestController implements BaseRestController<Genre, Integer> {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public String getEntity() {
        return GENRE;
    }

    @Override
    public JpaRepository<Genre, Integer> getRepository() {
        return genreRepository;
    }

    @Operation(summary = "Ajout d'un nouveau genre.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Genre.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au genre trouvé."),
        @ApiResponse(responseCode = "302", description = "Le genre existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le genre existe déjà."),
        @ApiResponse(responseCode = "404", description = "Genre non trouvé.")
    })
    @Override
    public ResponseEntity<Genre> addObject(Genre object) {
        if (object.getGenreId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getGenreId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Genre genre = genreRepository.findByName(object.getName());
        if (genre != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        genre = getRepository().save(object);
        if (genre != null && genre.getGenreId() != null) {
            return new ResponseEntity<>(genre, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un genre avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Genre.class))},
                description = "Genre trouvé."),
        @ApiResponse(responseCode = "404", description = "Genre non trouvé.")
    })
    @Override
    public ResponseEntity<Genre> getObject(Genre object) {
        Genre genre = getRepository().getOne(object.getGenreId());
        if (genre != null) {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un genre avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Genre.class))},
                description = "Genre trouvé."),
        @ApiResponse(responseCode = "404", description = "Genre non trouvé.")
    })
    @Override
    public ResponseEntity<Genre> getObject(Integer idObject) {
        Genre genre = getRepository().getOne(idObject);
        if (genre != null) {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un genre avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Genre.class))}, description = "Genre trouvé."),
        @ApiResponse(responseCode = "404", description = "Genre non trouvé."),
        @ApiResponse(responseCode = "304", description = "Genre non modifié.")
    })
    @Override
    public ResponseEntity<Genre> putObject(Genre object) {
        if (!getRepository().existsById(object.getGenreId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Genre genre = getRepository().save(object);
        if (genre != null) {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un genre.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Genre supprimé."),
        @ApiResponse(responseCode = "404", description = "Genre non trouvé.")
    })
    @Override
    public ResponseEntity<Genre> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un genre.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Genre supprimé."),
        @ApiResponse(responseCode = "404", description = "Genre non trouvé.")
    })
    @Override
    public ResponseEntity<Genre> deleteObject(Genre object) {
        if (getRepository().existsById(object.getGenreId())) {
            getRepository().deleteById(object.getGenreId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Genre genre = genreRepository.findByName(object.getName());
        if (genre != null) {
            getRepository().delete(genre);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les genres.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste des genres trouvés.")
    })
    @Override
    public ResponseEntity<List<Genre>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(
                Collections.singletonList(genreRepository.findByName(valeur)), HttpStatus.OK);
    }

    @Operation(summary = "Liste les genres.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de genres trouvés.")
    })
    @Override
    public ResponseEntity<List<Genre>> listObjects() {
        return new ResponseEntity<>(genreRepository.findAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error
                -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

}
