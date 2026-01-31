package com.revshop.dao;

import java.sql.*;
import com.revshop.util.DBConnection;

public class NotificationDAO {
	public void addNotification(int userId,String role, String message) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO notifications (user_id,role ,message,created_at) VALUES (?,?, ?,SYSTIMESTAMP)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, role);
        ps.setString(3,message);
        ps.executeUpdate();

        ps.close();
        con.close();

}
	public void viewNotifications(int userId, String role) throws SQLException {

	    String sql = "SELECT message, created_at FROM notifications " +
	                 "WHERE user_id = ? AND role = ? ORDER BY created_at DESC";

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    boolean found = false;

	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(sql);

	        ps.setInt(1, userId);
	        ps.setString(2, role);

	        rs = ps.executeQuery();

	        System.out.println("==== Notifications ====");

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getString("created_at") + " | " +
	                rs.getString("message")
	            );
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {

	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (con != null) con.close();
	    }

	    if (!found) {
	        System.out.println("order placed by user.");
	    }
        rs.close();
        ps.close();
        con.close();
}
}
   
 