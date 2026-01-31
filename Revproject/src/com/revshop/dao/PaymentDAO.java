package com.revshop.dao;
import java.sql.*;
import com.revshop.util.DBConnection;
public class PaymentDAO {
	public int getLatestOrderId(int userId) throws SQLException {
        Connection con = DBConnection.getConnection();

        String sql =
            "SELECT MAX(order_id) FROM orders WHERE user_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        int orderId = 0;

        if (rs.next()) {
            orderId = rs.getInt(1);
        }

        rs.close();
        ps.close();
        con.close();

        return orderId;
    }

    public void updatePayment(int orderId, String method) throws SQLException {
        Connection con = DBConnection.getConnection();

        String sql =
            "UPDATE orders SET payment_method = ?, payment_status = 'PAID' " +
            "WHERE order_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, method);
        ps.setInt(2, orderId);

        ps.executeUpdate();

        ps.close();
        con.close();
    }

}