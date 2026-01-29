package com.revshop.service;
import java.sql.SQLException;
import com.revshop.dao.NotificationDAO;
public class NotificationService {
	 private NotificationDAO dao = new NotificationDAO();

	    public void notifyOrderPlaced(int userId) throws SQLException {
	        dao.addNotification(userId, "Your order has been placed successfully!");
	    }

	    public void showNotifications(int userId) throws SQLException {
	        dao.viewNotifications(userId);
	    }

}
