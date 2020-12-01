package com.walterj.crusty.dao;

public interface IdentifiableEntity extends Identifiable {

    String getName();

    void setName(String n);

    String getDescription();

    void setDescription(String d);
}
