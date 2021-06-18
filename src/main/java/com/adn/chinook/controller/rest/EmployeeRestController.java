/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.controller.rest;

import com.adn.chinook.base.BaseRestController;
import com.adn.chinook.entity.Employee;
import com.adn.chinook.repository.EmployeeRepository;
import static com.adn.chinook.utils.Paths.EMPLOYE;
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
@RequestMapping(REST_VERSION + EMPLOYE)
public class EmployeeRestController implements BaseRestController<Employee, Integer> {

    @Autowired
    private EmployeeRepository employeRepository;

    @Override
    public String getEntity() {
        return EMPLOYE;
    }

    @Override
    public JpaRepository<Employee, Integer> getRepository() {
        return employeRepository;
    }

    @Operation(summary = "Ajout d'un nouveau employe.", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class))},
                description = "Succès de l'enregistrement ou de la modification s'il existe déjà."),
        @ApiResponse(responseCode = "304", description = "Aucune modification apportée à l'employe trouvé."),
        @ApiResponse(responseCode = "302", description = "L'employé existe déjà."),
        @ApiResponse(responseCode = "409", description = "L'employé existe déjà."),
        @ApiResponse(responseCode = "404", description = "Employé non trouvé.")
    })
    @Override
    public ResponseEntity<Employee> addObject(Employee object) {
        if (object.getEmployeeId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (getRepository().existsById(object.getEmployeeId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Employee employe = getRepository().save(object);
        if (employe != null && employe.getEmployeeId() != null) {
            return new ResponseEntity<>(employe, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Sélection d'un employe avec ses infromations.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class))},
                description = "Employee trouvé."),
        @ApiResponse(responseCode = "404", description = "Employee non trouvé.")
    })
    @Override
    public ResponseEntity<Employee> getObject(Employee object) {
        Employee employe = getRepository().getOne(object.getEmployeeId());
        if (employe != null) {
            return new ResponseEntity<>(employe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sélection d'un employe avec son id.",
            method = "GET"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class))},
                description = "Employee trouvé."),
        @ApiResponse(responseCode = "404", description = "Employee non trouvé.")
    })
    @Override
    public ResponseEntity<Employee> getObject(Integer idObject) {
        Employee employe = getRepository().getOne(idObject);
        if (employe != null) {
            return new ResponseEntity<>(employe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Modification d'un employe avec ses informations.", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class))},
                description = "Employee trouvé."),
        @ApiResponse(responseCode = "404", description = "Employee non trouvé."),
        @ApiResponse(responseCode = "304", description = "Employee non modifié.")
    })
    @Override
    public ResponseEntity<Employee> putObject(Employee object) {
        if (!getRepository().existsById(object.getEmployeeId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Employee employe = getRepository().save(object);
        if (employe != null) {
            return new ResponseEntity<>(employe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Suppression d'un employe.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Employee supprimé."),
        @ApiResponse(responseCode = "404", description = "Employee non trouvé.")
    })
    @Override
    public ResponseEntity<Employee> deleteObject(Integer idObject) {
        if (getRepository().existsById(idObject)) {
            getRepository().deleteById(idObject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Suppression d'un employe.", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Employee supprimé."),
        @ApiResponse(responseCode = "404", description = "Employee non trouvé.")
    })
    @Override
    public ResponseEntity<Employee> deleteObject(Employee object) {
        if (getRepository().existsById(object.getEmployeeId())) {
            getRepository().deleteById(object.getEmployeeId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Liste les employes.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste de employes trouvés.")
    })
    @Override
    public ResponseEntity<List<Employee>> listObjects(String attribut, String valeur) {
        switch (attribut) {
            case "firstName":
                return new ResponseEntity<>(employeRepository.findByFirstName(valeur), HttpStatus.OK);
            case "lastName":
                return new ResponseEntity<>(employeRepository.findByLastName(valeur), HttpStatus.OK);
            case "title":
                return new ResponseEntity<>(employeRepository.findByTitle(valeur), HttpStatus.OK);
            default:
                return new ResponseEntity<>(employeRepository.findAll(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Liste les employés.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))},
                description = "Liste des employés trouvés.")
    })
    @Override
    public ResponseEntity<List<Employee>> listObjects() {
        return new ResponseEntity<>(employeRepository.findAll(), HttpStatus.OK);
    }

//    @Bean
//    @RouterOperation(beanClass = EmployeeService.class, beanMethod = "findAllEmployees")
//    RouterFunction<ServerResponse> getAllEmployeesRoute() {
//        return route(GET("/employees").and(accept(MediaType.APPLICATION_JSON)),
//                req -> ok().body(
//                        employeeService().findAllEmployees(), Employee.class));
//    }
//    @Bean
//    @RouterOperation(operation = @Operation(operationId = "findEmployeeById", summary = "Find purchase order by ID", tags = {"MyEmployee"},
//            parameters = {
//                @Parameter(in = ParameterIn.PATH, name = "id", description = "Employee Id")},
//            responses = {
//                @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Employee.class))),
//                @ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied"),
//                @ApiResponse(responseCode = "404", description = "Employee not found")}))
//    RouterFunction<ServerResponse> getEmployeeByIdRoute() {
//        return route(GET("/employees/{id}"),
//                req -> ok().body(
//                        employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class));
//    }
//    @RouterOperations({
//        @RouterOperation(path = "/getAllPersons", beanClass = PersonService.class, beanMethod = "getAll"),
//        @RouterOperation(path = "/getPerson/{id}", beanClass = PersonService.class, beanMethod = "getOne"),
//        @RouterOperation(path = "/createPerson", beanClass = PersonService.class, beanMethod = "save"),
//        @RouterOperation(path = "/deletePerson/{id}", beanClass = PersonService.class, beanMethod = "delete")})
//    @Bean
//    public RouterFunction<ServerResponse> personRoute(PersonHandler handler) {
//        return RouterFunctions
//                .route(GET("/getAllPersons").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
//                .andRoute(GET("/getPerson/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
//                .andRoute(POST("/createPerson").and(accept(MediaType.APPLICATION_JSON)), handler::save)
//                .andRoute(DELETE("/deletePerson/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
//    }
}
