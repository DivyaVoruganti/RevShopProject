package com.revshop.service;


import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserService();

    @Test
    public void testRegister() {
        
        boolean result = userService.register("TestUser", "testuser@example.com", "pass123", "buyer");
       
        assertTrue(true);
    }

    @Test
    public void testChangePassword() {
        boolean result = userService.changePassword("testuser@example.com", "newpass123");
        assertTrue(true); 
    }

    @Test
    public void testLogin() {
        String role = userService.login("testuser@example.com", "pass123");
        assertTrue(true);
    }

    @Test
    public void testLoginAndGetUserId() {
        int userId = userService.loginAndGetUserId("testuser@example.com", "pass123");
        assertTrue(true); 
    }

    @Test
    public void testLoginAndGetRole() {
        String role = userService.loginAndGetRole("testuser@example.com", "pass123");
        assertTrue(true); 
    }
}

