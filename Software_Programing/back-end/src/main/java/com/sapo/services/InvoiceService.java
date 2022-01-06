package com.sapo.services;

import com.sapo.entities.Invoice;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    public void createInvoice(Invoice invoice);

    public Invoice findById(int invoiceId);

    public Invoice findNotDoneFollowVehicle(int vehicleId);

    public void updateInvoiceStatus(Integer invoiceId, Integer status);
}
