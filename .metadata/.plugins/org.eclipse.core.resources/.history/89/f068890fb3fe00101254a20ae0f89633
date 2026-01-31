package com.revshop.service;

import java.sql.SQLException;
import com.revshop.dao.ProductDAO;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    
    public void viewAllProducts(int sellerId) throws SQLException {
        productDAO.getProductsBySeller(sellerId);
    }

    
    public void viewAllProducts() throws SQLException {
        productDAO.viewAllProducts();
    }

   
    public void searchProducts(String keyword) throws SQLException {
        productDAO.searchByKeyword(keyword);
    }
}
