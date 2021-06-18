package com.adn.chinook.repository;

import com.adn.chinook.entity.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cagecfi
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByAdresse(String adresse);

    List<Address> findByCity(String address);

    List<Address> findByPhone(String phone);

    List<Address> findByEmail(String email);

    List<Address> findByCountry(String country);
}
