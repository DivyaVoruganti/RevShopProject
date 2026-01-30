package com.revshop.service;
import static org.junit.Assert.*;
import org.junit.Test;

public class OrderServiceTest {

    OrderService service = new OrderService();

    @Test
    public void testViewOrdersBySeller() throws Exception {
        service.viewOrdersBySeller(10);
        assertTrue(true);
    }

    @Test
    public void testViewOrderHistory() {
        service.viewOrderHistory(1);
        assertTrue(true);
    }
}

