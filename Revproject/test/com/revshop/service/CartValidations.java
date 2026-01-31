package com.revshop.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revshop.dao.cartDAO;
import com.revshop.dao.ProductDAO;

public class CartValidations {

    @Mock
    private cartDAO cartDAO;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private NotificationService notificationService;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // ✅ JUnit 4 compatible
    }

    @Test
    public void testAddProductWithStockCheck_Success() {
        when(productDAO.getAvailableStock(101)).thenReturn(5);

        boolean result = cartService.addProductWithStockCheck(1, 101, 2);

        assertTrue(result);
        verify(cartDAO).addToCart(1, 101, 2);
    }
}
