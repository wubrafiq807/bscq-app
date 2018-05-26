package com.location.rafiqul.bcsq.model;

import java.util.Date;

/**
 * Created by RAFIQUL on 2018-05-19.
 */

public class Subcategory extends Category{
    private int categoryId;


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }
public Subcategory(){}

    public Subcategory(int satus, Integer id, String name, String description, int categoryId) {
        super(name,description,id,satus);
        this.categoryId = categoryId;

    }

    public Subcategory(int createdBy, int modifiedBy, Date modifiedDate, int satus, int id, String name, String description, int categoryId) {
        super(createdBy, modifiedBy, modifiedDate, satus, id, name, description);
        this.categoryId = categoryId;
    }
}
