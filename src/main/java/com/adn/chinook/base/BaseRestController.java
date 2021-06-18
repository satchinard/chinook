/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.base;

import static com.adn.chinook.utils.Paths.CHINOOK_ADD;
import static com.adn.chinook.utils.Paths.CHINOOK_ALL;
import static com.adn.chinook.utils.Paths.CHINOOK_DELETE;
import static com.adn.chinook.utils.Paths.CHINOOK_GET;
import static com.adn.chinook.utils.Paths.CHINOOK_UPDATE;
import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author cagecfi
 * @param <I>
 * @param <PK>
 */
public interface BaseRestController<I, PK extends Serializable> {

    String getEntity();

//    @GetMapping("memory-status")
//    public MemoryStats getMemoryStatistics() {
//        MemoryStats stats = new MemoryStats();
//        stats.setHeapSize(Runtime.getRuntime().totalMemory());
//        stats.setHeapMaxSize(Runtime.getRuntime().maxMemory());
//        stats.setHeapFreeSize(Runtime.getRuntime().freeMemory());
//        return stats;
//    }
    /**
     *
     * @return
     */
    JpaRepository<I, PK> getRepository();

    @PostMapping(CHINOOK_ADD)
    ResponseEntity<I> addObject(@RequestBody @Valid I object);

    @GetMapping(CHINOOK_GET)
    ResponseEntity<I> getObject(@RequestBody I object);

    @GetMapping(CHINOOK_GET + "/{id}")
    ResponseEntity<I> getObject(@PathVariable("id") @Positive PK idObject);

    @PutMapping(CHINOOK_UPDATE)
    ResponseEntity<I> putObject(@RequestBody @Valid I object);

    @GetMapping(CHINOOK_DELETE + "/{id}")
    ResponseEntity<I> deleteObject(@PathVariable("id") @Positive PK idObject);

    @DeleteMapping(CHINOOK_DELETE)
    ResponseEntity<I> deleteObject(@RequestBody I object);

    @GetMapping(CHINOOK_ALL + "/{attribut}/{valeur}")
    ResponseEntity<List<I>> listObjects(@PathVariable("attribut") String attribut,
            @PathVariable("valeur") String valeur);

    @GetMapping(CHINOOK_ALL)
    ResponseEntity<List<I>> listObjects();

}
