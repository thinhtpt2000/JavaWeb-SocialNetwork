/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblcomments;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ThinhTPT
 */
public class TblCommentsDto implements Serializable {

    private String commentId;
    private String content;
    private String articleId;
    private String userId;
    private int status;
    private Timestamp time;

    public TblCommentsDto() {
    }

    public TblCommentsDto(String commentId, String content, String articleId, String userId, int status, Timestamp time) {
        this.commentId = commentId;
        this.content = content;
        this.articleId = articleId;
        this.userId = userId;
        this.status = status;
        this.time = time;
    }

    public TblCommentsDto(String commentId, String content, String userId, Timestamp time, int status) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.time = time;
        this.status = status;
    }

    public TblCommentsDto(String content, String articleId, String userId, int status, Timestamp time) {
        this.content = content;
        this.articleId = articleId;
        this.userId = userId;
        this.status = status;
        this.time = time;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
