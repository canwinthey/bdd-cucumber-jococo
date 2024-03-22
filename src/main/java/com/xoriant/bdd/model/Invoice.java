package com.xoriant.bdd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Invoice {

    private String erp;
    private String paymentType;
    private double amount;

    public Invoice(String erp, String paymentType, double amount){
        this.erp = erp;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public Invoice(String erp, String paymentType){
        this.erp = erp;
        this.paymentType = paymentType;
    }

}
