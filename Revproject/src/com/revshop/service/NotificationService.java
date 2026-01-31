package com.revshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revshop.dao.NotificationDAO;
import com.revshop.util.DBConnection;

public class NotificationService {

    private static final Logger logger = Logger.getLogger(NotificationService.class);

    private NotificationDAO notificationDAO = new NotificationDAO();

    public void showNotifications(int userId) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            String sql =
                    "SELECT message, created_at FROM notifications " +
                    "WHERE user_id=? ORDER BY created_at DESC";

            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            boolean found = false;
            logger.info("Fetching notifications for userId: " + userId);

            while (rs.next()) {
                found = true;
                logger.info(
                        rs.getString("message") +
                        " | " +
                        rs.getDate("created_at")
                );
            }

            if (!found) {
                logger.info("No notifications found for userId: " + userId);
            }

        } catch (SQLException e) {
            logger.error("Error while fetching notifications for userId: " + userId, e);
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                logger.error("Error closing DB resources in showNotifications", e);
            }
        }
    }

    public void notifySeller(int sellerId, String message) throws SQLException {
        notificationDAO.addNotification(sellerId, "SELLER", message);
        logger.info("Notification sent to sellerId: " + sellerId + ", Message: " + message);
    }

    public void notifyBuyer(int buyerId, String message) throws SQLException {
        notificationDAO.addNotification(buyerId, "BUYER", message);
        logger.info("Notification sent to buyerId: " + buyerId + ", Message: " + message);
    }

    public void viewSellerNotifications(int sellerId) throws SQLException {
        logger.info("Viewing seller notifications for sellerId: " + sellerId);
        notificationDAO.viewNotifications(sellerId, "SELLER");
    }

    public void viewBuyerNotifications(int buyerId) throws SQLException {
        logger.info("Viewing buyer notifications for buyerId: " + buyerId);
        notificationDAO.viewNotifications(buyerId, "BUYER");
    }
}
