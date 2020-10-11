/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblnotifications;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thinhtpt.utils.DBUtil;

/**
 *
 * @author ThinhTPT
 */
public class TblNotificationsDao implements Serializable {

    public boolean addReactionNotification(TblNotificationsDto dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblNotifications (notificationId, reactionId, eventType, time) "
                    + "VALUES (?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getId());
            stm.setString(2, dto.getReactionId());
            stm.setInt(3, dto.getEventType());
            stm.setTimestamp(4, dto.getTime());
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

    public boolean updateReactionNotification(TblNotificationsDto dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "UPDATE tblNotifications "
                    + "SET eventType = ?, time = ? "
                    + "WHERE reactionId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, dto.getEventType());
            stm.setTimestamp(2, dto.getTime());
            stm.setString(3, dto.getReactionId());
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

    public boolean addCommentNotification(TblNotificationsDto dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblNotifications (notificationId, commentId, eventType, time) "
                    + "VALUES (?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getId());
            stm.setString(2, dto.getCommentId());
            stm.setInt(3, dto.getEventType());
            stm.setTimestamp(4, dto.getTime());
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

    public boolean deleteCommentNotification(int eventType, String commentId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "UPDATE tblNotifications "
                    + "SET eventType = ? "
                    + "WHERE commentId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, eventType);
            stm.setString(2, commentId);
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

    public List<TblNotificationsDto> getNotificationsByAllEvent(
            List<String> listReactionIds, List<String> listCommentIds, int pageInt
    )
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblNotificationsDto> listNoti = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT notificationId, eventType, commentId, reactionId, time "
                    + "FROM tblNotifications "
                    + "WHERE " + DBUtil.setPreparedStamentForArray(listReactionIds.size(), "reactionId");
            if (!listReactionIds.isEmpty() && !listCommentIds.isEmpty()) {
                sql += "OR ";
            }
            sql += DBUtil.setPreparedStamentForArray(listCommentIds.size(), "commentId")
                    + "ORDER BY time DESC "
                    + "OFFSET (?-1)*10 ROWS "
                    + "FETCH NEXT 10 ROWS ONLY";
            stm = con.prepareStatement(sql);
            int counter = 1;
            for (String param : listReactionIds) {
                stm.setString(counter, param);
                counter++;
            }
            for (String param : listCommentIds) {
                stm.setString(counter, param);
                counter++;
            }
            stm.setInt(counter, pageInt);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (listNoti == null) {
                    listNoti = new ArrayList<>();
                }
                String id = rs.getString("notificationId");
                int eventType = rs.getInt("eventType");
                String commentId = rs.getString("commentId");
                String reactionId = rs.getString("reactionId");
                Timestamp time = rs.getTimestamp("time");
                TblNotificationsDto dto = new TblNotificationsDto(id, eventType, time);
                dto.setCommentId(commentId);
                dto.setReactionId(reactionId);
                listNoti.add(dto);
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
        return listNoti;
    }
}
