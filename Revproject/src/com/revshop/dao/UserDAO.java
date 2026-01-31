package com.revshop.dao;

import java.sql.*;
import com.revshop.util.DBConnection;

public class UserDAO {

    public boolean register(String name, String email, String password, String role) {
        boolean status = false;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)"
            );
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
           
            int i = ps.executeUpdate();
            if(i>0) status=true;
            con.close();
        } catch(Exception e){ e.printStackTrace(); }
        return status;
    }

    public boolean login(String email, String password) {
        boolean status=false;
        try {
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) status=true;
            con.close();
        } catch(Exception e){ e.printStackTrace(); }
        return status;
    }

    public boolean changePassword(String email, String newPassword) {
        boolean status=false;
        try {
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                "UPDATE users SET password=? WHERE email=?"
            );
            ps.setString(1,newPassword);
            ps.setString(2,email);
            int i=ps.executeUpdate();
            if(i>0) status=true;
            con.close();
        } catch(Exception e){ e.printStackTrace(); }
        return status;
    }

public String loginAndGetRole(String email, String password) {
    String role = null;
    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT role FROM users WHERE email=? AND password=?"
        );
        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            role = rs.getString("role");
        }
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return role;
}
public ResultSet loginAndGetUser(String email, String password) {
    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT user_id, role FROM users WHERE email=? AND password=?"
        );
        ps.setString(1, email);
        ps.setString(2, password);
        return ps.executeQuery();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
public String getRoleByUserId(int userId) {

    String role = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String sql = "SELECT role FROM users WHERE user_id=?";

    try {
        con = DBConnection.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        rs = ps.executeQuery();
        if (rs.next()) {
            role = rs.getString("role");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) {}
        try { if (ps != null) ps.close(); } catch (SQLException e) {}
        try { if (con != null) con.close(); } catch (SQLException e) {}
    }

    return role;
}


}