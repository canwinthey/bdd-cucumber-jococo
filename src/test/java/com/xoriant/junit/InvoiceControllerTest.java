package com.xoriant.junit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.xoriant.bdd.controller.InvoiceController;
import com.xoriant.bdd.model.Invoice;
import com.xoriant.bdd.service.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
/*
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ComponentScan(basePackages = "com.xoriant.bdd.service")
@AutoConfigureMockMvc(addFilters = false)
*/
//@SpringBootTest(classes = InvoiceController.class,
//        properties = {"server.port=8080"})
//@RunWith(SpringRunner.class)
//@WebMvcTest(value= InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountService discountService;

    /*
    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }
    */

    //@Test
    public void testGetDiscountForInvoice() throws Exception {
        // Define test scenarios
        List<String> erps = Arrays.asList("Netsuite", "Netsuite", "Netsuite", "Microsoft Dynamics", "Microsoft Dynamics",
                "Microsoft Dynamics", "Netsuite", "Netsuite", "", "Microsoft Dynamics", "Microsoft Dynamics",
                "", "Quickbooks", "null", "Microsoft Dynamics");
        List<String> paymentTypes = Arrays.asList("CreditCard", "ACH", "WIRE", "DebitCard", "MobilePayment",
                "EFT", "Cash", "", "ACH", "", "Cash", "", "ACH", "ACH", "null");
        List<String> discounts = Arrays.asList("10%", "15%", "20%", "30%", "25%",
                "20%", "Invalid payment type", "Invalid payment type", "Invalid ERP", "Invalid payment type",
                "Invalid payment type", "Invalid ERP", "Invalid ERP", "Invalid ERP", "Invalid payment type");

        // Iterate through scenarios
        //for (int i = 0; i < erps.size(); i++) {
            String erp = erps.get(0);
            String paymentType = paymentTypes.get(0);
            String expectedDiscount = discounts.get(0);

            // Mock Invoice object
            Invoice invoice = new Invoice();
            invoice.setErp(erp);
            invoice.setPaymentType(paymentType);
            invoice.setAmount(100.00);

            Mockito.when(discountService.getDiscountForInvoice(Mockito.any(Invoice.class))).thenReturn(expectedDiscount);
            //Mockito.when(discountService.getDiscountForInvoice(any())).thenReturn(expectedDiscount);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(invoice);
            System.out.println(json);

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/invoice/discount")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = result.getResponse();

            String output = response.getContentAsString();

            assertThat(output).isEqualTo(json);
            assertEquals(HttpStatus.OK.value(), response.getStatus());

            System.out.println("status: " + result.getResponse().getStatus());
            System.out.println("output: " + output);

            /*
            MvcResult result = mockMvc.perform(get("/invoice/discount").content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();


            // Verifies that the method was called exactly once with any Invoice object
            Mockito.verify(discountService, Mockito.times(1))
                    .getDiscountForInvoice(Mockito.any(Invoice.class));


            Assertions.assertEquals(expectedDiscount, result.getResponse().getContentAsString());
            */
        //}
    }
}
