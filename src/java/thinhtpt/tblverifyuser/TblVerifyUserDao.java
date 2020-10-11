/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblverifyuser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import thinhtpt.utils.DBUtil;

/**
 *
 * @author ThinhTPT
 */
public class TblVerifyUserDao implements Serializable {

    public int countNumberOfValidCode(String email, Timestamp currentTime)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT COUNT(id) as count "
                    + "FROM tblVerifyUser "
                    + "WHERE email = ? "
                    + "AND expireTime > ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setTimestamp(2, currentTime);
            rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count;
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
        return 0;
    }

    public boolean checkVerifyCode(String email, String code, Timestamp currentTime)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT id "
                    + "FROM tblVerifyUser "
                    + "WHERE email = ? "
                    + "AND expireTime >= ? "
                    + "AND code = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setTimestamp(2, currentTime);
            stm.setString(3, code);
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

    public boolean addNewCode(String id, String email, String code, Timestamp requestTime, Timestamp expireTime)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblVerifyUser (id, email, code, requestTime, expireTime) "
                    + "VALUES (?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, email);
            stm.setString(3, code);
            stm.setTimestamp(4, requestTime);
            stm.setTimestamp(5, expireTime);
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

    public boolean deleteCodesByUser(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "DELETE FROM tblVerifyUser WHERE email = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
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

    public Timestamp getLastRequestTime(String email, Timestamp currentTime)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT requestTime "
                    + "FROM tblVerifyUser "
                    + "WHERE email = ? "
                    + "AND expireTime > ? "
                    + "ORDER BY expireTime ASC";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setTimestamp(2, currentTime);
            rs = stm.executeQuery();
            if (rs.next()) {
                Timestamp requestTime = rs.getTimestamp("requestTime");
                return requestTime;
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
}
