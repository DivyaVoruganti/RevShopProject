package com.revshop.dao;

import com.revshop.model.Product;
import com.revshop.util.DBConnection;

import java.sql.*;

public class ProductDAO {

    
    public void viewAllProducts() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT product_id, name, price, discount_price, stock, category FROM products";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("ID | Name | MRP | Discount | Stock | Category");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("product_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getDouble("price") + " | " +
                    rs.getDouble("discount_price") + " | " +
                    rs.getInt("stock") + " | " +
                    rs.getString("category")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    
    public void searchByKeyword(String keyword) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT product_id, name, price, discount_price, stock, category " +
                     "FROM products WHERE LOWER(name) LIKE ? OR LOWER(category) LIKE ?";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);

            String value = "%" + keyword.toLowerCase() + "%";
            ps.setString(1, value);
            ps.setString(2, value);

            rs = ps.executeQuery();
            System.out.println("ID | Name | MRP | Discount | Stock | Category");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("product_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getDouble("price") + " | " +
                    rs.getDouble("discount_price") + " | " +
                    rs.getInt("stock") + " | " +
                    rs.getString("category")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    // ================= ADD PRODUCT =================
    public void addProduct(Product product) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO products " +
                     "(product_id, name, price, discount_price, stock, category, description, seller_id) " +
                     "VALUES (product_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getMrp());
            ps.setDouble(3, product.getDiscountPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getDescription());
            ps.setInt(7, product.getSellerId());

            ps.executeUpdate();
            System.out.println("Product added successfully!");
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    
    public void getProductsBySeller(int sellerId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT product_id, name, price, discount_price, stock, category FROM products WHERE seller_id=?";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sellerId);
            rs = ps.executeQuery();

            System.out.println("ID | Name | MRP | Discount | Stock | Category");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("product_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getDouble("price") + " | " +
                    rs.getDouble("discount_price") + " | " +
                    rs.getInt("stock") + " | " +
                    rs.getString("category")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

   
    public void deleteProduct(int productId, int sellerId) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM products WHERE product_id=? AND seller_id=?";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, sellerId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Product deleted!" : "Delete failed!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    
    public void updateProduct(Product product) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE products SET name=?, price=?, discount_price=?, stock=?, category=?, description=? " +
                     "WHERE product_id=? AND seller_id=?";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setDouble(3, product.getDiscountPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getDescription());
            ps.setInt(7, product.getProductId());
            ps.setInt(8, product.getSellerId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    public void updateProductPrice(Product product) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE products SET price=?, discount_price=? WHERE product_id=? AND seller_id=?";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, product.getMrp());
            ps.setDouble(2, product.getDiscountPrice());
            ps.setInt(3, product.getProductId());
            ps.setInt(4, product.getSellerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    public void viewProductReviewsBySeller(int sellerId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.name, r.rating, r.comment FROM reviews r JOIN products p ON r.product_id = p.product_id WHERE p.seller_id=?";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sellerId);
            rs = ps.executeQuery();

            System.out.println("Product Name | Rating | Comment");
            while (rs.next()) {
                System.out.println(
                    rs.getString("name") + " | " +
                    rs.getInt("rating") + " | " +
                    rs.getString("comment")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    public void updateStockThreshold(int productId, int sellerId, int threshold) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE products SET stock_threshold=? WHERE product_id=? AND seller_id=?";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, threshold);
            ps.setInt(2, productId);
            ps.setInt(3, sellerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    public int getAvailableStock(int productId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int stock = -1;
        String sql = "SELECT stock FROM products WHERE product_id=?";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return stock;
    }

    // ================= REDUCE STOCK =================
    public void reduceStock(int productId, int quantity, Connection con) throws SQLException {
        PreparedStatement ps = null;
        String sql = "UPDATE products SET stock = stock - ? WHERE product_id=?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, quantity);
        ps.setInt(2, productId);
        ps.executeUpdate();
        ps.close();
    }

    // ================= LOW STOCK CHECK =================
    public void checkLowStock(int sellerId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT product_id, name, stock, stock_threshold FROM products WHERE seller_id=? AND stock <= stock_threshold";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sellerId);
            rs = ps.executeQuery();

            boolean alertFound = false;
            System.out.println("LOW STOCK ALERTS");
            System.out.println("ProductID | Name | Stock | Threshold");
            while (rs.next()) {
                alertFound = true;
                System.out.println(
                    rs.getInt("product_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("stock") + " | " +
                    rs.getInt("stock_threshold")
                );
            }
            if (!alertFound) {
                System.out.println("All products are sufficiently stocked");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
}