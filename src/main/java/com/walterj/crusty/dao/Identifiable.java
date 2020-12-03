package com.walterj.crusty.dao;

/**
 * @author Walter Jordan
 */
public interface Identifiable {

    static final long NEW_ID = -1L;

    long getId();
}
