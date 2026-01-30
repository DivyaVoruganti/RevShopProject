package com.revshop.service;

import java.util.Scanner;
import java.sql.SQLException;
import com.revshop.dao.ProductDAO;
import com.revshop.model.Product;
import com.revshop.service.UserService;
import com.revshop.dao.UserDAO;

public class SellerService {

    private ProductDAO productDAO = new ProductDAO();
    private UserService userService = new UserService();
    private UserDAO userDAO = new UserDAO();

    /* =================== ADD PRODUCT =================== */
    public void addProduct(String name, double mrp, double discountPrice,
                           int stock, String category, String description, int sellerId) {

        if (!isSeller(sellerId)) {
            System.out.println("Error: Only sellers can add products.");
            return;
        }

        if (discountPrice > mrp) {
            System.out.println("Error: Discount price cannot be greater than MRP.");
            return;
        }

        Product product = new Product();
        product.setName(name);
        product.setMrp(mrp);
        product.setDiscountPrice(discountPrice);
        product.setStock(stock);
        product.setCategory(category);
        product.setDescription(description);
        product.setSellerId(sellerId);

        try {
            productDAO.addProduct(product);
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.out.println("Product NOT added! Reason: " + e.getMessage());
        }
    }

    /* =================== CHECK SELLER ROLE =================== */
    private boolean isSeller(int userId) {
        String role = userDAO.getRoleByUserId(userId);
        return "seller".equalsIgnoreCase(role);
    }

    private boolean checkIfSeller(int userId) {
        String role = userService.getRoleByUserId(userId);
        return "seller".equalsIgnoreCase(role);
    }

    /* =================== VIEW SELLER PRODUCTS =================== */
    public void viewSellerProducts(int sellerId) {
        try {
            productDAO.getProductsBySeller(sellerId);
        } catch (Exception e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
    }

    /* =================== UPDATE PRODUCT =================== */
    public void updateProduct(int productId, int sellerId, String name, double mrp,
                              double discountPrice, int stock, String category, String description) {

        if (discountPrice > mrp) {
            System.out.println("Discount price cannot be greater than MRP");
            return;
        }

        Product product = new Product();
        product.setProductId(productId);
        product.setSellerId(sellerId);
        product.setName(name);
        product.setMrp(mrp);
        product.setDiscountPrice(discountPrice);
        product.setStock(stock);
        product.setCategory(category);
        product.setDescription(description);

        try {
            productDAO.updateProduct(product);
            System.out.println("Product updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    /* =================== UPDATE PRICE =================== */
    public void updatePrice(int sellerId) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Product ID to update price: ");
            int productId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New MRP: ");
            double newMrp = Double.parseDouble(sc.nextLine());

            System.out.print("Enter New Discount Price: ");
            double newDiscount = Double.parseDouble(sc.nextLine());

            if (newDiscount > newMrp) {
                System.out.println("Discount price cannot be greater than MRP");
                return;
            }

            Product product = new Product();
            product.setProductId(productId);
            product.setSellerId(sellerId);
            product.setMrp(newMrp);
            product.setDiscountPrice(newDiscount);

            try {
                productDAO.updateProductPrice(product);
                System.out.println("Price updated successfully!");
            } catch (Exception e) {
                System.out.println("Error updating price: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Enter numeric values for MRP and Discount Price.");
        }
    }

    /* =================== VIEW PRODUCT REVIEWS =================== */
    public void viewProductReviews(int sellerId) {
        try {
            productDAO.viewProductReviewsBySeller(sellerId);
        } catch (Exception e) {
            System.out.println("Error fetching product reviews: " + e.getMessage());
        }
    }

    /* =================== SET INVENTORY THRESHOLD =================== */
    public void setInventoryThreshold(int sellerId) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Product ID: ");
            int productId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Stock Threshold Value: ");
            int threshold = Integer.parseInt(sc.nextLine());

            try {
                productDAO.updateStockThreshold(productId, sellerId, threshold);
                System.out.println("Threshold updated successfully!");
            } catch (Exception e) {
                System.out.println("Error updating threshold: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Enter numeric values only.");
        }
    }

    /* =================== CHECK LOW STOCK ALERTS =================== */
    public void checkLowStockAlerts(int sellerId) {
        try {
            productDAO.checkLowStock(sellerId);
        } catch (Exception e) {
            System.out.println("Error checking low stock: " + e.getMessage());
        }
    }

    /* =================== DELETE PRODUCT =================== */
    public void deleteProduct(int productId, int sellerId) {
        try {
            productDAO.deleteProduct(productId, sellerId);
            System.out.println("Product deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    /* =================== SELLER MENU =================== */
    public void sellerMenu(int sellerId, Scanner sc) {
        while (true) {
            System.out.println("\n===== SELLER MENU =====");
            System.out.println("1. View My Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Update Product Price");
            System.out.println("5. Delete Product");
            System.out.println("6. View Product Reviews");
            System.out.println("7. Set Inventory Threshold");
            System.out.println("8. Check Low Stock Alerts");
            System.out.println("9. Logout");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter numbers only.");
                continue;
            }

            switch (choice) {
                case 1:
                    viewSellerProducts(sellerId);
                    break;
                case 2:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("MRP: ");
                    double mrp = Double.parseDouble(sc.nextLine());
                    System.out.print("Discount Price: ");
                    double discount = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(sc.nextLine());
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    addProduct(name, mrp, discount, stock, category, desc, sellerId);
                    break;
                case 3:
                    System.out.print("Enter Product ID to update: ");
                    int pid = Integer.parseInt(sc.nextLine());
                    System.out.print("New Name: ");
                    String n = sc.nextLine();
                    System.out.print("New MRP: ");
                    double pMrp = Double.parseDouble(sc.nextLine());
                    System.out.print("New Discount Price: ");
                    double pDiscount = Double.parseDouble(sc.nextLine());
                    System.out.print("New Stock: ");
                    int pStock = Integer.parseInt(sc.nextLine());
                    System.out.print("New Category: ");
                    String pCategory = sc.nextLine();
                    System.out.print("New Description: ");
                    String pDesc = sc.nextLine();

                    updateProduct(pid, sellerId, n, pMrp, pDiscount, pStock, pCategory, pDesc);
                    break;
                case 4:
                    updatePrice(sellerId);
                    break;
                case 5:
                    System.out.print("Enter Product ID to delete: ");
                    int delId = Integer.parseInt(sc.nextLine());
                    deleteProduct(delId, sellerId);
                    break;
                case 6:
                    viewProductReviews(sellerId);
                    break;
                case 7:
                    setInventoryThreshold(sellerId);
                    break;
                case 8:
                    checkLowStockAlerts(sellerId);
                    break;
                case 9:
                    System.out.println("Logging out of seller menu...");
                    return; // logout
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
