package com.xoriant.bdd;

import com.xoriant.bdd.model.Invoice;
import com.xoriant.bdd.service.DiscountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


/**
 * BDD test for Discount Service
 */
public class DiscountServiceTest {

	DiscountService discountService = new DiscountService();
	Invoice invoice = null;
	String discount = "";

	@Given("an invoice with erp {string} and payment type {string}")
	public void an_invoice_with_erp_and_payment_type(String erp, String paymentType) {
		invoice = new Invoice(erp, paymentType, 1000.50);
	}

	@When("the getDiscountForInvoice method is called")
	public void the_getDiscountForInvoice_method_is_called() {
		discount = discountService.getDiscountForInvoice(invoice);
	}

	@Then("the discount percentage value should be {string}")
	public void the_discount_percentage_value_should_be(String value) {
		Assert.assertEquals(value, discount);
	}


}