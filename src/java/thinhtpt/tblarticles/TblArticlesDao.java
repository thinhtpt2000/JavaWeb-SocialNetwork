/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblarticles;

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
public class TblArticlesDao implements Serializable {

    public List<TblArticlesDto> getArticlesByName(String searchValue, int statusId, int pageNumber)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<TblArticlesDto> listPosts = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT id, title, description, imageLink, date "
                    + "FROM tblArticles WHERE description LIKE ? ESCAPE '\\'"
                    + "AND status = ? "
                    + "ORDER BY date DESC "
                    + "OFFSET (?-1)*20 ROWS "
                    + "FETCH NEXT 20 ROWS ONLY";
            stm = con.prepareStatement(sql);
            stm.setNString(1, "%" + searchValue + "%");
            stm.setInt(2, statusId);
            stm.setInt(3, pageNumber);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (listPosts == null) {
                    listPosts = new ArrayList<>();
                }
                String id = rs.getString("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String imgLink = rs.getString("imageLink");
                Timestamp date = rs.getTimestamp("date");
                TblArticlesDto dto = new TblArticlesDto(id, title, description, imgLink, date);
                listPosts.add(dto);
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
        return listPosts;
    }

    public int getTotalArticleByName(String searchValue, int statusId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT COUNT(id) AS total "
                    + "FROM tblArticles "
                    + "WHERE description LIKE ? ESCAPE '\\' AND status = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + searchValue + "%");
            stm.setInt(2, statusId);
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

    public TblArticlesDto getArticleById(String id, int statusId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT title, description, imageLink, date, postBy "
                    + "FROM tblArticles WHERE id = ? "
                    + "AND status = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setInt(2, statusId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");;
                String imgLink = rs.getString("imageLink");
                Timestamp date = rs.getTimestamp("date");
                String owner = rs.getString("postBy");
                TblArticlesDto dto = new TblArticlesDto(id, title, description, imgLink, date, owner);
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

    public boolean addArticle(TblArticlesDto dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "INSERT INTO tblArticles (id, title, description, imageLink, date, status, postBy) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getId());
            stm.setNString(2, dto.getTitle());
            stm.setNString(3, dto.getDescription());
            stm.setString(4, dto.getImageLink());
            stm.setTimestamp(5, dto.getDate());
            stm.setInt(6, dto.getStatus());
            stm.setString(7, dto.getOwner());
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

    public boolean deleteArticle(String articleId, String userId, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            String sql = "UPDATE tblArticles "
                    + "SET status = ? "
                    + "WHERE id = ? AND postBy = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setString(2, articleId);
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

    public List<String> getArticleIdsByOwner(String ownerId, int statusId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<String> listIds = new ArrayList<>();

        try {
            con = DBUtil.makeConnection();
            String sql = "SELECT id "
                    + "FROM tblArticles WHERE postBy = ? "
                    + "AND status = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, ownerId);
            stm.setInt(2, statusId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
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

//    public String getOwnerByArticleId(String id)
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBUtil.makeConnection();
//            String sql = "SELECT postBy "
//                    + "FROM tblArticles WHERE id = ?";
//            stm = con.prepareStatement(sql);
//            stm.setString(1, id);
//            rs = stm.executeQuery();
//            if (rs.next()) {
//                String owner = rs.getString("postBy");
//                return owner;
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
}
