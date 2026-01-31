package com.revshop.dao;

import java.sql.*;
import com.revshop.util.DBConnection;

public class NotificationDAO {
	public void addNotification(int userId, String message) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO notifications (user_id, message) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, message);
        ps.executeUpdate();

        ps.close();
        con.close();

}

    public void viewNotifications(int userId) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT message, created_at FROM notifications WHERE user_id = ? ORDER BY created_at DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        System.out.println("\n===== NOTIFICATIONS =====");
        boolean hasAny = false;

        while (rs.next()) {
            hasAny = true;
            System.out.println("- " + rs.getString("message"));
        }

        if (!hasAny) {
            System.out.println("No notifications.");
        }

        rs.close();
        ps.close();
        con.close();
    }
}
