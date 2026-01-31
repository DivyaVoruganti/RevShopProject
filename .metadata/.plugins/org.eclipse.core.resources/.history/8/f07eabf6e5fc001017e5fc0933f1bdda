package com.revshop.dao;
import java.sql.*;
import com.revshop.util.DBConnection;
public class OrderDAO {
	 public void viewOrderHistory(int userId) throws SQLException {
	        Connection con = DBConnection.getConnection();

	        String sql =
	            "SELECT o.order_id, o.order_date, o.total_amount " +
	            "FROM orders o " +
	            "WHERE o.user_id = ? " +
	            "ORDER BY o.order_date DESC";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n===== ORDER HISTORY =====");
	        System.out.println("Order ID | Date | Total");

	        while (rs.next()) {
	            System.out.println(
	                rs.getInt("order_id") + " | " +
	                rs.getDate("order_date") + " | " +
	                rs.getDouble("total_amount")
	            );

	            viewOrderItems(rs.getInt("order_id"), con);
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    }

	    private void viewOrderItems(int orderId, Connection con) throws SQLException {
	        String sql =
	            "SELECT p.name, oi.quantity, oi.price " +
	            "FROM order_items oi " +
	            "JOIN products p ON oi.product_id = p.product_id " +
	            "WHERE oi.order_id = ?";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, orderId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            System.out.println(
	                "   - " + rs.getString("name") +
	                " | Qty: " + rs.getInt("quantity") +
	                " | Price: " + rs.getDouble("price")
	            );
	        }

	        rs.close();
	        ps.close();
	    }

}
