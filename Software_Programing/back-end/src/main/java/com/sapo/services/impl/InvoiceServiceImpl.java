package com.sapo.services.impl;

import com.sapo.entities.Invoice;
import com.sapo.repositories.InvoiceRepository;
import com.sapo.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class.toString());
    private InvoiceRepository invoiceRepository;
    @Override
    public void createInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
