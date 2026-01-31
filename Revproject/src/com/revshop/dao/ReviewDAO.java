package com.revshop.dao;
import java.sql.*;
import com.revshop.util.DBConnection;
public class ReviewDAO {
	 public boolean hasPurchased(int userId, int productId) throws SQLException {
	        Connection con = DBConnection.getConnection();

	        String sql =
	            "SELECT 1 FROM orders o " +
	            "JOIN order_items oi ON o.order_id = oi.order_id " +
	            "WHERE o.user_id = ? AND oi.product_id = ?";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ps.setInt(2, productId);

	        ResultSet rs = ps.executeQuery();
	        boolean purchased = rs.next();

	        rs.close();
	        ps.close();
	        con.close();

	        return purchased;
	    }

	  
	    public void addReview(int userId, int productId, int rating, String text)
	            throws SQLException {

	        Connection con = DBConnection.getConnection();

	        String sql =
	            "INSERT INTO reviews VALUES (review_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ps.setInt(2, productId);
	        ps.setInt(3, rating);
	        ps.setString(4, text);

	        ps.executeUpdate();

	        ps.close();
	        con.close();
	    }

	   
	    public void viewProductReviews(int productId) throws SQLException {
	        Connection con = DBConnection.getConnection();

	        String sql =
	            "SELECT rating, review_text, review_date " +
	            "FROM reviews WHERE product_id = ?";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, productId);

	        ResultSet rs = ps.executeQuery();

	        System.out.println("Rating | Review | Date");
	        while (rs.next()) {
	            System.out.println(
	                rs.getInt("rating") + " | " +
	                rs.getString("review_text") + " | " +
	                rs.getDate("review_date")
	            );
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    }
	}

