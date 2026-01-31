package com.revshop.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revshop.dao.OrderDAO;

public class OrderValidations {

    @Mock
    private OrderDAO orderDao;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    
    @Test
    public void testViewOrdersBySeller() throws SQLException {

        orderService.viewOrdersBySeller(101);

        verify(orderDao, times(1))
                .viewOrdersBySeller(101);
    }

   
    @Test
    public void testViewOrderHistory_Success() throws SQLException {

        
        doNothing().when(orderDao).viewOrderHistory(202);

        orderService.viewOrderHistory(202);

        verify(orderDao, times(1))
                .viewOrderHistory(202);
    }

    
    @Test
    public void testViewOrderHistory_ExceptionHandled() throws SQLException {

        doThrow(new SQLException("DB Error"))
                .when(orderDao).viewOrderHistory(303);

        
        orderService.viewOrderHistory(303);

        verify(orderDao, times(1))
                .viewOrderHistory(303);
    }
}
