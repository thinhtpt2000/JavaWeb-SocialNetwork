/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblnotifications;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ThinhTPT
 */
public class TblNotificationsDto implements Serializable {

    private String id;
    private int eventType;
    private Timestamp time;
    private String reactionId;
    private String commentId;

    public TblNotificationsDto() {
    }

    public TblNotificationsDto(int eventType, Timestamp time, String reactionId) {
        this.eventType = eventType;
        this.time = time;
        this.reactionId = reactionId;
    }

    public TblNotificationsDto(String id, int eventType, Timestamp time) {
        this.id = id;
        this.eventType = eventType;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getReactionId() {
        return reactionId;
    }

    public void setReactionId(String reactionId) {
        this.reactionId = reactionId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

}
