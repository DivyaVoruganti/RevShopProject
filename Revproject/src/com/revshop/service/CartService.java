package com.revshop.service;
import com.revshop.dao.cartDAO;
public class CartService {
	private cartDAO cartDAO = new cartDAO();

    public void addProduct(int buyerId, int productId, int qty) {
        cartDAO.addToCart(buyerId, productId, qty);
    }

    public void viewCart(int buyerId) {
        cartDAO.viewCart(buyerId);
    }

    public void removeProduct(int buyerId, int productId) {
        cartDAO.removeFromCart(buyerId, productId);
    }

}
