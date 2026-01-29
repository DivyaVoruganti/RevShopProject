package com.revshop.service;
import java.sql.SQLException;
import com.revshop.dao.ProductDAO;
import com.revshop.model.Product;
public class SellerService {
	  private ProductDAO productDAO = new ProductDAO();

	    public void sellerMenu(int sellerId) {
	        // optional – menu already handled in main
	    }

	    public void addProduct(int sellerId, String name, double price,
	                           int stock, String category, String desc) {

	        Product p = new Product();
	        p.setSellerId(sellerId);
	        p.setName(name);
	        p.setPrice(price);
	        p.setStock(stock);
	        p.setCategory(category);
	        p.setDescription(desc);

	        productDAO.addProduct(p);
	    }

	    public void viewSellerProducts(int sellerId) {
	        productDAO.viewProductsBySeller(sellerId);
	    }

	    public void updateProduct(int pid, int sellerId, String name,
	                              double price, int stock,
	                              String category, String desc) {

	        Product p = new Product();
	        p.setProductId(pid);
	        p.setSellerId(sellerId);
	        p.setName(name);
	        p.setPrice(price);
	        p.setStock(stock);
	        p.setCategory(category);
	        p.setDescription(desc);

	        productDAO.updateProduct(p);
	    }

	    public void deleteProduct(int productId, int sellerId) {
	        productDAO.deleteProduct(productId, sellerId);
	    }
	}
	

