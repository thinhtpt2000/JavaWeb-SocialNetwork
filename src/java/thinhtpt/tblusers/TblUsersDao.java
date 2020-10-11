/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblusers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import thinhtpt.utils.DBUtil;

/**
 *
 * @author ThinhTPT
 */
public class TblUsersDao implements Serializable {

    public boolean createNewAccount(String email, String password, String fullName, int statusId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblUsers(email, password, name, statusId) "
                    + "VALUES (?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setNString(3, fullName);
            stm.setInt(4, statusId);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public TblUsersDto checkLogin(String email, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT name, statusId FROM tblUsers WHERE email = ? AND password = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                TblUsersDto dto = new TblUsersDto(email, rs.getString("name"), rs.getInt("statusId"));
                return dto;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public String getFullNameById(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT name FROM tblUsers WHERE email = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("name");
                return fullName;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean changeUserStatus(String email, int statusId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "UPDATE tblUsers "
                    + "SET statusId = ? "
                    + "WHERE email = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setString(2, email);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean isExistedEmail(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT name FROM tblUsers WHERE email = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
