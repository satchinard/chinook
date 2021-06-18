package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Address;
import com.adn.chinook.repository.AddressRepository;
import static com.adn.chinook.utils.Paths.ADDRESS;
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
@RequestMapping(REST_VERSION + ADDRESS)
public class AddressRestController implements BaseRestController<Address, Integer> {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String getEntity() {
        return ADDRESS;
    }

    @Override
    public JpaRepository<Address, Integer> getRepository() {
        return addressRepository;
    }

    @Operation(summary = "Ajout d'une nouvelle adresse.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Address.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304",
                description = "Aucune modification apportée au address trouvé.", content = @Content),
        @ApiResponse(responseCode = "302",
                description = "L'adresse existe déjà.", content = @Content),
        @ApiResponse(responseCode = "409",
                description = "L'adresse existe déjà.", content = @Content),
        @ApiResponse(responseCode = "404",
                description = "Adresse non trouvée.", content = @Content)
    })
    @Override
    public ResponseEntity<Address> addObject(Address object) {
        if (object.getAddressId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getAddressId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Address address = getRepository().save(object);
        if (address != null && address.getAddressId() != null) {
            return new ResponseEntity<>(address, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'une adresse avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Address.class))},
                description = "Adresse trouvée."),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée.")
    })
    @Override
    public ResponseEntity<Address> getObject(Address object) {
        Address address = getRepository().getOne(object.getAddressId());
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'une addresse avec son id.",
            method = "GET"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Address.class))},
                description = "Adresse trouvée."),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée.")
    })
    @Override
    public ResponseEntity<Address> getObject(Integer idObject) {
        Address address = getRepository().getOne(idObject);
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'une addresse avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Address.class))},
                description = "Adresse trouvée."),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée."),
        @ApiResponse(responseCode = "304", description = "Adresse non modifiée.")
    })
    @Override
    public ResponseEntity<Address> putObject(Address object) {
        if (!getRepository().existsById(object.getAddressId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Address address = getRepository().save(object);
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'une addresse.", method = "GET"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Adresse supprimée."),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée.")
    })
    @Override
    public ResponseEntity<Address> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'une adresse.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Adresse supprimée."),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée.")
    })
    @Override
    public ResponseEntity<Address> deleteObject(Address object) {
        if (getRepository().existsById(object.getAddressId())) {
            getRepository().deleteById(object.getAddressId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les adresses.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste des adresses trouvées.")
    })
    @Override
    public ResponseEntity<List<Address>> listObjects(String attribut, String valeur) {
        switch (attribut) {
            case "email":
                return new ResponseEntity<>(addressRepository.findByEmail(valeur), HttpStatus.OK);
            case "city":
                return new ResponseEntity<>(addressRepository.findByCity(valeur), HttpStatus.OK);
            case "adresse":
                return new ResponseEntity<>(addressRepository.findByAdresse(valeur), HttpStatus.OK);
            case "country":
                return new ResponseEntity<>(addressRepository.findByCountry(valeur), HttpStatus.OK);
            case "phone":
                return new ResponseEntity<>(addressRepository.findByPhone(valeur), HttpStatus.OK);
            default:
                return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Liste les adresses.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste des adresses trouvées.")
    })
    @Override
    public ResponseEntity<List<Address>> listObjects() {
        return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
    }

}
