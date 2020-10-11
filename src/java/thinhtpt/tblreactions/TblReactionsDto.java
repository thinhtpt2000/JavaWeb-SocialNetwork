/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblreactions;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ThinhTPT
 */
public class TblReactionsDto implements Serializable {

    private String reactionId;
    private String articleId;
    private String userId;
    private int typeId;
    private Timestamp time;

    public TblReactionsDto() {
    }

    public TblReactionsDto(String reactionId, int typeId) {
        this.reactionId = reactionId;
        this.typeId = typeId;
    }

    public TblReactionsDto(String reactionId, String articleId, String userId, int typeId, Timestamp time) {
        this.reactionId = reactionId;
        this.articleId = articleId;
        this.userId = userId;
        this.typeId = typeId;
        this.time = time;
    }

    public String getReactionId() {
        return reactionId;
    }

    public void setReactionId(String reactionId) {
        this.reactionId = reactionId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
