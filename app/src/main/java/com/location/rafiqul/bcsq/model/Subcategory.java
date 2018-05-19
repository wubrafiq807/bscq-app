package com.location.rafiqul.bcsq.model;

import java.util.Date;

/**
 * Created by RAFIQUL on 2018-05-19.
 */

public class Subcategory extends Category{
    private int categoryId;



    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public Subcategory() {

    }
    public Subcategory(int createdBy, int modifiedBy, Date modifiedDate, int satus, int id, int categoryId) {
        super(createdBy, modifiedBy, modifiedDate, satus, id);
        this.categoryId = categoryId;
    }
}
