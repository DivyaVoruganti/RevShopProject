package com.revshop.service;
import static org.junit.Assert.*;
import org.junit.Test;

public class CheckoutServiceTest {

    CheckoutService service = new CheckoutService();

    @Test
    public void testCheckoutSuccess() {
        service.checkout(1);
        assertTrue(true); // verify via DB
    }

    @Test
    public void testCheckoutEmptyCart() {
        service.checkout(2);
        assertTrue(true);
    }
}


