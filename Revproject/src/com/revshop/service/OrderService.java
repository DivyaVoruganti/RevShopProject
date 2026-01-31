package com.revshop.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revshop.dao.OrderDAO;

public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderService.class);

    private OrderDAO orderDao = new OrderDAO();

    public void viewOrdersBySeller(int sellerId) throws SQLException {
        logger.info("Fetching orders for sellerId: " + sellerId);
        orderDao.viewOrdersBySeller(sellerId);
    }

    public void viewOrderHistory(int userId) {
        try {
            logger.info("Fetching order history for userId: " + userId);
            orderDao.viewOrderHistory(userId);
        } catch (SQLException e) {
            logger.error("Error fetching order history for userId: " + userId, e);
        }
    }
}
