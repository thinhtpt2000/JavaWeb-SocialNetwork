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
public class TblNotificationInfo implements Serializable {

    private String id;
    private String articleId;
    private String notifierName;
    private int eventType;
    private Timestamp time;

    public TblNotificationInfo(String id, String articleId, String notifierName, int eventType, Timestamp time) {
        this.id = id;
        this.articleId = articleId;
        this.notifierName = notifierName;
        this.eventType = eventType;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName;
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

}
