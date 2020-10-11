/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblreactions;

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
public class TblReactionsDao implements Serializable {

    public int counterReactionByArticleId(String articleId, int typeId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT COUNT(reactionId) AS total FROM tblReactions "
                    + "WHERE articleId = ? AND typeId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, articleId);
            stm.setInt(2, typeId);
            rs = stm.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                return total;
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

    public TblReactionsDto getCurrentReaction(String articleId, String userId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT reactionId, typeId FROM tblReactions "
                    + "WHERE articleId = ? AND userId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, articleId);
            stm.setString(2, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String reactionId = rs.getString("reactionId");
                int typeId = rs.getInt("typeId");
                TblReactionsDto dto = new TblReactionsDto(reactionId, typeId);
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

    public boolean updateReaction(String reactionId, int typeId, Timestamp time)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "UPDATE tblReactions "
                    + "SET typeId = ?, time = ? "
                    + "WHERE reactionId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, typeId);
            stm.setTimestamp(2, time);
            stm.setString(3, reactionId);
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

    public boolean addReaction(TblReactionsDto dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblReactions(reactionId, articleId, userId, typeId, time) "
                    + "VALUES(?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getReactionId());
            stm.setString(2, dto.getArticleId());
            stm.setString(3, dto.getUserId());
            stm.setInt(4, dto.getTypeId());
            stm.setTimestamp(5, dto.getTime());
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

    public List<String> getListReactionIdsByArticle(List<String> articleIds, String articleOwner, int type)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<String> listIds = new ArrayList<>();

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT reactionId "
                    + "FROM tblReactions "
                    + "WHERE " + DBUtil.setPreparedStamentForArray(articleIds.size(), "articleId")
                    + "AND typeId != ? "
                    + "AND userId != ?";
            stm = con.prepareStatement(sql);
            int counter = 1;
            for (String articleId : articleIds) {
                stm.setString(counter, articleId);
                counter++;
            }
            stm.setInt(counter, type);
            stm.setString(++counter, articleOwner);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("reactionId");
                listIds.add(id);
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
        return listIds;
    }

    public String[] getOwnerAndArticleIdReactionById(String reactionId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT userId, articleId "
                    + "FROM tblReactions "
                    + "WHERE reactionId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, reactionId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String owner = rs.getString("userId");
                String articleId = rs.getString("articleId");
                return new String[]{owner, articleId};
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
