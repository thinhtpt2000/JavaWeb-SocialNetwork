/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblcomments;

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
public class TblCommentsDao implements Serializable {

    public boolean addComment(TblCommentsDto dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblComments (commentId, text, articleId, userId, status, time) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getCommentId());
            stm.setNString(2, dto.getContent());
            stm.setString(3, dto.getArticleId());
            stm.setString(4, dto.getUserId());
            stm.setInt(5, dto.getStatus());
            stm.setTimestamp(6, dto.getTime());
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

    public List<TblCommentsDto> getCommentsByArticle(String articleId, int statusId, int pageNumber)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<TblCommentsDto> listComments = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT commentId, text, userId, time "
                    + "FROM tblComments WHERE articleId = ? "
                    + "AND status = ? "
                    + "ORDER BY time DESC "
                    + "OFFSET (?-1)*10 ROWS "
                    + "FETCH NEXT 10 ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setString(1, articleId);
            stm.setInt(2, statusId);
            stm.setInt(3, pageNumber);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (listComments == null) {
                    listComments = new ArrayList<>();
                }
                String id = rs.getString("commentId");
                String content = rs.getString("text");
                String userId = rs.getString("userId");
                Timestamp time = rs.getTimestamp("time");
                TblCommentsDto dto = new TblCommentsDto(id, content, userId, time, statusId);
                listComments.add(dto);
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
        return listComments;
    }

    public int getTotalCommentByArticle(String articleId, int status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT COUNT(commentId) AS total "
                    + "FROM tblComments "
                    + "WHERE articleId = ? AND status = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, articleId);
            stm.setInt(2, status);
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

    public boolean deleteComment(String commentId, String userId, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "UPDATE tblComments "
                    + "SET status = ? "
                    + "WHERE commentId = ? AND userId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setString(2, commentId);
            stm.setString(3, userId);

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

    public List<String> getListCommentIdsByArticle(List<String> articleIds, String articleOwner, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<String> listIds = new ArrayList<>();

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT commentId "
                    + "FROM tblComments "
                    + "WHERE " + DBUtil.setPreparedStamentForArray(articleIds.size(), "articleId")
                    + "AND status = ? "
                    + "AND userId != ?";
            stm = con.prepareStatement(sql);
            int counter = 1;
            for (String articleId : articleIds) {
                stm.setString(counter, articleId);
                counter++;
            }
            stm.setInt(counter, status);
            stm.setString(++counter, articleOwner);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("commentId");
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

    public String[] getOwnerAndArticleIdCommentById(String commentId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT userId, articleId "
                    + "FROM tblComments "
                    + "WHERE commentId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, commentId);
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
