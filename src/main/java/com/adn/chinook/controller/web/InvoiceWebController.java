package com.adn.chinook.controller.web;

import com.adn.chinook.base.BaseWebController;
import com.adn.chinook.entity.Invoice;
import com.adn.chinook.form.InvoiceForm;
import com.adn.chinook.repository.CustomerRepository;
import com.adn.chinook.repository.InvoiceRepository;
import static com.adn.chinook.utils.Paths.INVOICE;
import static com.adn.chinook.utils.Paths.WEB_VERSION;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping(WEB_VERSION + INVOICE)
public class InvoiceWebController implements BaseWebController<Invoice, Integer, InvoiceForm> {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public JpaRepository<Invoice, Integer> getRepository() {
        return invoiceRepository;
    }

    @Override
    public String getWebControllerPath() {
        return WEB_VERSION + INVOICE;
    }

    @Override
    public ModelAndView defaultPath() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invoices", invoiceRepository.findAll());
        mav.setViewName("invoice/all");
        return mav;
    }

    @Override
    public ModelAndView addObject(Invoice i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("allCustomers", customerRepository.findAll());
            mav.setViewName("invoice/add");
            return mav;
        }
        if (i.getInvoiceDate() != null) {
            invoiceRepository.save(i);
        }
        mav.addObject("invoices", invoiceRepository.findAll());
        mav.setViewName("invoice/all");
        return mav;
    }

    @Override
    public ModelAndView getObject(Integer pk) {
        Invoice invoice = null;
        try {
            invoice = invoiceRepository.getOne(pk);
        } catch (EntityNotFoundException enfe) {
        }
        ModelAndView mav = new ModelAndView();
        if (invoice != null && invoice.getInvoiceId() != null) {
            mav.addObject("allCustomers", customerRepository.findAll());
            mav.addObject("invoice", invoice);
            mav.setViewName("invoice/get");
            return mav;
        }
        mav.setViewName("invoice/add");
        return mav;
    }

    @Override
    public ModelAndView updateObject(Integer pk) {
        Invoice invoice = invoiceRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (invoice != null) {
            mav.addObject("allCustomers", customerRepository.findAll());
            mav.addObject("invoice", invoice);
            mav.setViewName("invoice/update");
            return mav;
        }
        mav.setViewName("invoice/add");
        return mav;
    }

    @Override
    public ModelAndView deleteObject(Integer pk) {
        Invoice invoice = invoiceRepository.getOne(pk);
        ModelAndView mav = new ModelAndView();
        if (invoice != null) {
            try {
                invoiceRepository.delete(invoice);
            } catch (DataIntegrityViolationException dive) {
            }
        }
        mav.addObject("invoices", invoiceRepository.findAll());
        mav.setViewName("invoice/all");
        return mav;
    }

    @Override
    public ModelAndView listObjects(Invoice i, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invoices", invoiceRepository.findAll());
        mav.setViewName("invoice/all");
        return mav;
    }

}
