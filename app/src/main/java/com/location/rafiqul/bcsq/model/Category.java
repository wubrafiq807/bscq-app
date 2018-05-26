package com.location.rafiqul.bcsq.model;

import java.util.Date;

/**
 * Created by RAFIQUL on 2018-05-19.
 */

public class Category extends CommonProp{

    private String name;
    private String description;

    public Category() {

    }

    public Category(String name, String description,Integer id,int status) {
        super(id, status);
        this.name = name;
        this.description = description;
    }

    private Date created_date;

    public Category(int createdBy, int modifiedBy, Date modifiedDate, int satus, int id,String name,String description) {
        super(createdBy, modifiedBy, modifiedDate, satus,id);
        this.name=name;
        this.description=description;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
