package com.revshop.service;
import java.sql.SQLException;
import com.revshop.dao.OrderDAO;

public class OrderService {
	private OrderDAO orderDao = new OrderDAO();

    public void viewOrderHistory(int userId) {
        try {
            orderDao.viewOrderHistory(userId);
        } catch (SQLException e) {
            System.out.println("Error fetching order history");
            e.printStackTrace();
        }
    }

}
