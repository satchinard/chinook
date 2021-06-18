/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adn.chinook.service;

import com.adn.chinook.entity.Customer;
import com.adn.chinook.entity.Invoice;
import com.adn.chinook.repository.InvoiceRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cagecfi
 */
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository repository) {
        this.invoiceRepository = repository;
    }

    List<Invoice> findByInvoiceDate(Date invoice) {
        return this.invoiceRepository.findByInvoiceDate(invoice);
    }

    List<Invoice> findByCity(Customer customer) {
        return this.invoiceRepository.findByCustomer(customer);
    }

}
