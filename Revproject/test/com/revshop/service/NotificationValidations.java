package com.revshop.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revshop.dao.NotificationDAO;

public class NotificationValidations {

    @Mock
    private NotificationDAO notificationDAO;

    @InjectMocks
    private NotificationService notificationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    
    @Test
    public void testNotifySeller() throws SQLException {

        notificationService.notifySeller(10, "New order received");

        verify(notificationDAO)
                .addNotification(10, "SELLER", "New order received");
    }

    
    @Test
    public void testNotifyBuyer() throws SQLException {

        notificationService.notifyBuyer(20, "Order placed successfully");

        verify(notificationDAO)
                .addNotification(20, "BUYER", "Order placed successfully");
    }

    
    @Test
    public void testViewSellerNotifications() throws SQLException {

        notificationService.viewSellerNotifications(15);

        verify(notificationDAO)
                .viewNotifications(15, "SELLER");
    }

    
    @Test
    public void testViewBuyerNotifications() throws SQLException {

        notificationService.viewBuyerNotifications(25);

        verify(notificationDAO)
                .viewNotifications(25, "BUYER");
    }
}
