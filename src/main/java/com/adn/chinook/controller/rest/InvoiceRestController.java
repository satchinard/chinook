/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Invoice;
import com.adn.chinook.repository.InvoiceRepository;
import static com.adn.chinook.utils.Paths.INVOICE;
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
@RequestMapping(REST_VERSION + INVOICE)
public class InvoiceRestController implements BaseRestController<Invoice, Integer> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public String getEntity() {
        return INVOICE;
    }

    @Override
    public JpaRepository<Invoice, Integer> getRepository() {
        return invoiceRepository;
    }

    @Operation(summary = "Ajout d'un nouveau reçu.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Invoice.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée au reçu trouvé."),
        @ApiResponse(responseCode = "302", description = "Le reçu existe déjà."),
        @ApiResponse(responseCode = "409", description = "Le reçu existe déjà."),
        @ApiResponse(responseCode = "404", description = "Reçu non trouvé.")
    })
    @Override
    public ResponseEntity<Invoice> addObject(Invoice object) {
        if (object.getInvoiceId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getInvoiceId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Invoice invoice = getRepository().save(object);
        if (invoice != null && invoice.getInvoiceId() != null) {
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un reçu avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Invoice.class))},
                description = "Reçu trouvé."),
        @ApiResponse(responseCode = "404", description = "Reçu non trouvé.")
    })
    @Override
    public ResponseEntity<Invoice> getObject(Invoice object) {
        Invoice invoice = getRepository().getOne(object.getInvoiceId());
        if (invoice != null) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un reçu avec son id.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Invoice.class))},
                description = "Reçu trouvé."),
        @ApiResponse(responseCode = "404", description = "Reçu non trouvé.")
    })
    @Override
    public ResponseEntity<Invoice> getObject(Integer idObject) {
        Invoice invoice = getRepository().getOne(idObject);
        if (invoice != null) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un reçu avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Invoice.class))},
                description = "Reçu trouvé."),
        @ApiResponse(responseCode = "404", description = "Reçu non trouvé."),
        @ApiResponse(responseCode = "304", description = "Reçu non modifié.")
    })
    @Override
    public ResponseEntity<Invoice> putObject(Invoice object) {
        if (!getRepository().existsById(object.getInvoiceId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Invoice invoice = getRepository().save(object);
        if (invoice != null) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un reçu.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reçu supprimé."),
        @ApiResponse(responseCode = "404", description = "Reçu non trouvé.")
    })
    @Override
    public ResponseEntity<Invoice> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un reçu.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reçu supprimé."),
        @ApiResponse(responseCode = "404", description = "Reçu non trouvé.")
    })
    @Override
    public ResponseEntity<Invoice> deleteObject(Invoice object) {
        if (getRepository().existsById(object.getInvoiceId())) {
            getRepository().deleteById(object.getInvoiceId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les reçus.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))}, description = "Liste de reçus trouvés.")
    })
    @Override
    public ResponseEntity<List<Invoice>> listObjects(String attribut, String valeur) {
        return new ResponseEntity<>(invoiceRepository.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Liste les reçus.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))}, description = "Liste de reçus trouvés.")
    })
    @Override
    public ResponseEntity<List<Invoice>> listObjects() {
        return new ResponseEntity<>(invoiceRepository.findAll(), HttpStatus.OK);
    }

}
