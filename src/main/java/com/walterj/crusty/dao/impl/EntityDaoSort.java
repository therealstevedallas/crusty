package com.walterj.crusty.dao.impl;

/**
 * Class to carry sort info around
 * @author Walter Jordan
 */
public class EntityDaoSort {

    String name = "name";
    boolean ascending;

    public EntityDaoSort(String property, boolean asc) {
        name = property;
        ascending = asc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
