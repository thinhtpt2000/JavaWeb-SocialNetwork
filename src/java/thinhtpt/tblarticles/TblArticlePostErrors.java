/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblarticles;

import java.io.Serializable;

/**
 *
 * @author ThinhTPT
 */
public class TblArticlePostErrors implements Serializable {

    private String titleLengthErr;
    private String descriptionLengthErr;
    private String fileInvalidErr;

    public String getTitleLengthErr() {
        return titleLengthErr;
    }

    public void setTitleLengthErr(String titleLengthErr) {
        this.titleLengthErr = titleLengthErr;
    }

    public String getDescriptionLengthErr() {
        return descriptionLengthErr;
    }

    public void setDescriptionLengthErr(String descriptionLengthErr) {
        this.descriptionLengthErr = descriptionLengthErr;
    }

    public String getFileInvalidErr() {
        return fileInvalidErr;
    }

    public void setFileInvalidErr(String fileInvalidErr) {
        this.fileInvalidErr = fileInvalidErr;
    }

}
