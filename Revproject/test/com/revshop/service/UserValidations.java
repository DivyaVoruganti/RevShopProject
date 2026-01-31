package com.revshop.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revshop.dao.UserDAO;

public class UserValidations {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegister_Success() {
        when(userDAO.register("Alice", "alice@example.com", "pass123", "buyer")).thenReturn(true);

        boolean result = userService.register("Alice", "alice@example.com", "pass123", "buyer");

        assertTrue(result);
        verify(userDAO).register("Alice", "alice@example.com", "pass123", "buyer");
    }

    @Test
    public void testRegister_Failure() {
        when(userDAO.register("Bob", "bob@example.com", "pass123", "seller")).thenReturn(false);

        boolean result = userService.register("Bob", "bob@example.com", "pass123", "seller");

        assertFalse(result);
        verify(userDAO).register("Bob", "bob@example.com", "pass123", "seller");
    }

    @Test
    public void testChangePassword_Success() {
        when(userDAO.changePassword("alice@example.com", "newpass")).thenReturn(true);

        boolean result = userService.changePassword("alice@example.com", "newpass");

        assertTrue(result);
        verify(userDAO).changePassword("alice@example.com", "newpass");
    }

    @Test
    public void testChangePassword_Failure() {
        when(userDAO.changePassword("bob@example.com", "newpass")).thenReturn(false);

        boolean result = userService.changePassword("bob@example.com", "newpass");

        assertFalse(result);
        verify(userDAO).changePassword("bob@example.com", "newpass");
    }

    @Test
    public void testLogin_Success() {
        when(userDAO.loginAndGetRole("alice@example.com", "pass123")).thenReturn("buyer");

        String role = userService.login("alice@example.com", "pass123");

        assertEquals("buyer", role);
        verify(userDAO).loginAndGetRole("alice@example.com", "pass123");
    }

    @Test
    public void testLogin_Failure() {
        when(userDAO.loginAndGetRole("bob@example.com", "wrongpass")).thenReturn(null);

        String role = userService.login("bob@example.com", "wrongpass");

        assertNull(role);
        verify(userDAO).loginAndGetRole("bob@example.com", "wrongpass");
    }

}
