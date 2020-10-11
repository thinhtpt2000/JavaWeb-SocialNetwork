/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblarticles;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ThinhTPT
 */
public class TblArticlesDto implements Serializable {

    private String id;
    private String title;
    private String description;
    private String imageLink;
    private Timestamp date;
    private int status;
    private String owner;

    public TblArticlesDto() {
    }

    public TblArticlesDto(String id, String title, String description, String imageLink, Timestamp date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
        this.date = date;
    }

    public TblArticlesDto(String id, String title, String description, String imageLink, Timestamp date, String owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
        this.date = date;
        this.owner = owner;
    }

    public TblArticlesDto(String id, String title, String description, String imageLink, Timestamp date, int status, String owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
        this.date = date;
        this.status = status;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
