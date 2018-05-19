package com.location.rafiqul.bcsq.model;

import java.util.Date;

/**
 * Created by RAFIQUL on 2018-05-19.
 */

public class CommonProp {
    private int id;
    private int CreatedBy;
    private int modifiedBy;
    private Date modifiedDate;
    private int satus;
public CommonProp(){}

    public CommonProp(int createdBy, int modifiedBy, Date modifiedDate, int satus,int id) {
        CreatedBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.satus = satus;
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int createdBy) {
        CreatedBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getSatus() {
        return satus;
    }

    public void setSatus(int satus) {
        this.satus = satus;
    }


}
