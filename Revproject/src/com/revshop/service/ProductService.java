package com.revshop.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revshop.dao.ProductDAO;

public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class);

    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void viewAllProducts(int sellerId) throws SQLException {
        logger.info("Viewing products for sellerId: " + sellerId);
        productDAO.getProductsBySeller(sellerId);
    }

    public void viewAllProducts() throws SQLException {
        logger.info("Viewing all products");
        productDAO.viewAllProducts();
    }

    public void searchProducts(String keyword) throws SQLException {
        logger.info("Searching products with keyword: " + keyword);
        productDAO.searchByKeyword(keyword);
    }
}
