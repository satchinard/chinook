/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.repository;

import com.adn.chinook.entity.Artist;
import com.adn.chinook.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cagecfi
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByTitle(String title);

    List<Employee> findByArtist(Artist artist);

    Employee findByFirstNameAndLastName(String firstName, String lastName);
}
