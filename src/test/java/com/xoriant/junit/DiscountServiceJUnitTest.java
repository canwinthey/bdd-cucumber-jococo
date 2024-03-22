package com.xoriant.junit;

import com.xoriant.bdd.model.Invoice;
import com.xoriant.bdd.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceJUnitTest {

    private DiscountService discountService;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
    }

    @Test
    public void testDiscountForNetsuiteCreditCard() {
        Invoice invoice = new Invoice("Netsuite", "CreditCard");
        assertEquals("10%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForNetsuiteACH() {
        Invoice invoice = new Invoice("Netsuite", "ACH");
        assertEquals("15%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForNetsuiteWIRE() {
        Invoice invoice = new Invoice("Netsuite", "WIRE");
        assertEquals("20%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForMicrosoftDynamicsDebitCard() {
        Invoice invoice = new Invoice("Microsoft Dynamics", "DebitCard");
        assertEquals("30%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForMicrosoftDynamicsMobilePayment() {
        Invoice invoice = new Invoice("Microsoft Dynamics", "MobilePayment");
        assertEquals("25%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForMicrosoftDynamicsEFT() {
        Invoice invoice = new Invoice("Microsoft Dynamics", "EFT");
        assertEquals("20%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testInvalidPaymentType() {
        Invoice invoice = new Invoice("Netsuite", "InvalidPaymentType");
        assertEquals("Invalid payment type", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testInvalidERP() {
        Invoice invoice = new Invoice("InvalidERP", "CreditCard");
        assertEquals("Invalid ERP", discountService.getDiscountForInvoice(invoice));
    }
}
