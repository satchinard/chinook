/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.repository;

import com.adn.chinook.entity.Customer;
import com.adn.chinook.entity.Invoice;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cagecfi
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByInvoiceDate(Date invoiceDate);

    List<Invoice> findByCustomer(Customer customer);
}
