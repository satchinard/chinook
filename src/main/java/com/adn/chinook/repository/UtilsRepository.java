/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.repository;

import com.adn.chinook.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cagecfi
 */
@Repository
public interface UtilsRepository extends JpaRepository<Role, Long> {

//    @Query("DROP TABLE :table")
//    void dropTable(@Param("table") String table);
}
