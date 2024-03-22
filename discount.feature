Feature: Calculate Discount for payments

  Scenario Outline: Calculate discount for erp and payment types
    Given an invoice with erp "<erp>" and payment type "<paymentType>"
    When the getDiscountForInvoice method is called
    Then the discount percentage value should be "<discount>"
    Examples:
      | erp                 | paymentType     | discount             |
      | Netsuite            | CreditCard      | 10%                  |
      | Netsuite            | ACH             | 15%                  |
      | Netsuite            | WIRE            | 20%                  |
      | Netsuite            | Cash            | Invalid payment type |
      | Netsuite            |                 | Invalid payment type |
      |                     | ACH             | Invalid ERP          |
      | Microsoft Dynamics  | DebitCard       | 30%                  |
      | Microsoft Dynamics  | MobilePayment   | 25%                  |
      | Microsoft Dynamics  | EFT             | 20%                  |
      | Microsoft Dynamics  |                 | Invalid payment type |
      | Microsoft Dynamics  | Cash            | Invalid payment type |
      |                     |                 | Invalid ERP          |
      | Quickbooks          | ACH             | Invalid ERP          |
