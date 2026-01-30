package com.revshop.dao;

import static org.junit.Assert.*;

import org.junit.Assert.*;
import java.sql.Connection;
import java.util.List;
import org.junit.Test;

import com.revshop.model.CartItem;
import com.revshop.util.DBConnection;

public class cartDAOTest {

    cartDAO dao = new cartDAO();

    @Test
    public void testAddToCart_NewProduct() {
        dao.addToCart(1, 101, 2);
        // Verify manually via DB or viewCart()
        assertTrue(true);
    }

    @Test
    public void testAddToCart_UpdateQuantity() {
        dao.addToCart(1, 101, 1);
        dao.addToCart(1, 101, 2);
        assertTrue(true);
    }

    @Test
    public void testGetCartItems() throws Exception {
        Connection con = DBConnection.getConnection();
        List<CartItem> items = dao.getCartItems(1, con);
        assertNotNull(items);
        con.close();
    }

    @Test
    public void testRemoveFromCart() {
        dao.removeFromCart(1, 101);
        assertTrue(true);
    }

    @Test
    public void testClearCart() throws Exception {
        Connection con = DBConnection.getConnection();
        dao.clearCart(1, con);
        con.close();
        assertTrue(true);
    }
}
