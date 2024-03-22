package com.xoriant.bdd.service;

import com.xoriant.bdd.model.Invoice;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public String getDiscountForInvoice(Invoice invoice){

        String discount = "";
        if("Netsuite".equalsIgnoreCase(invoice.getErp())){
            if("CreditCard".equalsIgnoreCase(invoice.getPaymentType())) {
                discount = "10%";
            } else if("ACH".equalsIgnoreCase(invoice.getPaymentType())) {
                discount = "15%";
            } else if("WIRE".equalsIgnoreCase(invoice.getPaymentType())) {
                discount = "20%";
            } else {
                discount = "Invalid payment type";
            }
        } else if("Microsoft Dynamics".equalsIgnoreCase(invoice.getErp())){
            if("DebitCard".equalsIgnoreCase(invoice.getPaymentType())) {
                discount = "30%";
            } else if("MobilePayment".equalsIgnoreCase(invoice.getPaymentType())) {
                discount = "25%";
            } else if("EFT".equalsIgnoreCase(invoice.getPaymentType())) {
                discount = "20%";
            } else {
                discount = "Invalid payment type";
            }
        } else {
            discount = "Invalid ERP";
        }

        return discount;
    }
}
