/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.test.db;

import com.adn.chinook.entity.Address;
import com.adn.chinook.repository.AddressRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author CAGECFI
 */
//@RunWith(SpringRunner.class)
//@Configuration
//@EntityScan(basePackages = {"com.adn.chinook.entity"})
//@EnableJpaRepositories(basePackages = {"com.adn.chinook.repository"})
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AttributeEncryptionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeEncryptionTest.class);

    private static final String NAME = "John";
    private static final String EMAIL = "john@example.com";

    private int id;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AddressRepository addressRepository;

//    @BeforeAll
//    public static void setUp() {
//    }
    @Test
    public void readDecrypted() {
        assertThat(addressRepository).isNotNull();

        Address address = new Address();
        address.setAdresse(NAME);
        address.setEmail(EMAIL);
        id = addressRepository.save(address).getAddressId();

//        address = addressRepository.findById(id).orElseThrow();
//        assertThat(address.getAdresse()).isEqualTo(NAME);
//        assertThat(address.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void readEncrypted() {
        assertThat(jdbcTemplate).isNotNull();

        Address address = jdbcTemplate.queryForObject(
                "select * from address where address_id = ?",
                (resultSet, i) -> {
                    Address result = new Address();
                    result.setAddressId(resultSet.getInt("address_id"));
                    result.setAdresse(resultSet.getString("address"));
                    result.setEmail(resultSet.getString("email"));
                    return result;
                },
                id
        );

        assertThat(address.getAdresse()).isNotEqualTo(NAME);
        LOGGER.info("Encrypted name value in DB is {}", address.getAdresse());
        assertThat(address.getEmail()).isNotEqualTo(EMAIL);
        LOGGER.info("Encrypted email value in DB is {}", address.getEmail());
    }
}
