package com.chenery.socialcore.model;

import java.util.Date;

/**
 *
 */
public class StatusUpdate {

    private String comment;

    private Date updateTime;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
