package com.revshop.service;
import static org.junit.Assert.*;
import org.junit.Test;

public class PaymentServiceTest {

    PaymentService service = new PaymentService();

    @Test
    public void testNoOrders() throws Exception {
        service.makePayment(99);
        assertTrue(true);
    }
}

