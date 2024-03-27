package com.xoriant.junit;

import com.xoriant.bdd.model.Invoice;
import com.xoriant.bdd.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceJUnitTest {

    private static final Logger logger = LoggerFactory.getLogger(DiscountServiceJUnitTest.class);
    private static final String fileName = "discount.feature";
    private DiscountService discountService;

    @BeforeEach
    public void setUp() {
        this.discountService = new DiscountService();
    }

    @Test
    public void testDiscountForNetsuiteCreditCard() {
        logger.info(getFeatureFile("testDiscountForNetsuiteCreditCard"));
        Invoice invoice = new Invoice("Netsuite", "CreditCard");
        assertEquals("10%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForNetsuiteACH() {
        logger.info(getFeatureFile("testDiscountForNetsuiteACH"));
        Invoice invoice = new Invoice("Netsuite", "ACH");
        assertEquals("15%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForNetsuiteWIRE() {
        logger.info(getFeatureFile("testDiscountForNetsuiteWIRE"));
        Invoice invoice = new Invoice("Netsuite", "WIRE");
        assertEquals("20%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForMicrosoftDynamicsDebitCard() {
        logger.info(getFeatureFile("testDiscountForMicrosoftDynamicsDebitCard"));
        Invoice invoice = new Invoice("Microsoft Dynamics", "DebitCard");
        assertEquals("30%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForMicrosoftDynamicsMobilePayment() {
        logger.info(getFeatureFile("testDiscountForMicrosoftDynamicsMobilePayment"));
        Invoice invoice = new Invoice("Microsoft Dynamics", "MobilePayment");
        assertEquals("25%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testDiscountForMicrosoftDynamicsEFT() {
        logger.info(getFeatureFile("testDiscountForMicrosoftDynamicsEFT"));
        Invoice invoice = new Invoice("Microsoft Dynamics", "EFT");
        assertEquals("20%", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testInvalidPaymentType() {
        logger.info(getFeatureFile("testInvalidPaymentType"));
        Invoice invoice = new Invoice("Netsuite", "InvalidPaymentType");
        assertEquals("Invalid payment type", discountService.getDiscountForInvoice(invoice));
    }

    @Test
    public void testInvalidERP() {
        logger.info(getFeatureFile("testInvalidERP"));
        Invoice invoice = new Invoice("InvalidERP", "CreditCard");
        assertEquals("Invalid ERP", discountService.getDiscountForInvoice(invoice));
    }

    public static String getFeatureFile(String testCaseName) {
        return testCaseName + ": Running test case for feature: " + fileName;
    }
}
