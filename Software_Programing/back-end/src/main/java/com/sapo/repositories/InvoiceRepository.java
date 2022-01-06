package com.sapo.repositories;

import com.sapo.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {
    //@Query(value = "select invoices.* from invoices where invoices.vehicle_id = ?", nativeQuery = true)
    Invoice findInvoiceByVehicleIdAndStatus(int vehicleId, int status);
    Invoice findById(int invoiceId);

//    Invoice findInvoiceByVehicleIdAndStatus(int vehicleId, int status);
}
