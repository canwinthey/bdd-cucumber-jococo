package com.xoriant.bdd.controller;

import com.xoriant.bdd.model.Invoice;
import com.xoriant.bdd.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoice")
public class InvoiceController {

    @Autowired
    private DiscountService discountService;

    @GetMapping("/discount")
    public ResponseEntity<String> getDiscountForOrder(@RequestBody Invoice invoice){
        return new ResponseEntity<>(discountService.getDiscountForInvoice(invoice), HttpStatus.OK);
    }
}
