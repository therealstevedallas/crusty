package com.walterj.crusty.dao;

import java.util.List;

public interface Dao<T extends Identifiable> {

    /**
     * Create a new {@link Identifiable}
     * @param t An instance to create (Id = -1)
     * @return the Id of the entity after creation.
     */
    long add(T t);

    /**
     * Look up an instance of {@link Identifiable} by Id.
     * @param id The Id to look for {@link Identifiable} -> getId()
     * @return An instance of {@link Identifiable} or null.
     */
    T get(Class<T> type, long id);

    /**
     * Look up the stored instance using entity properties determined by
     * implementation. Can be used for natural key lookup.
     * @param t An instance of {@link Identifiable}
     * @return An instance or null
     */
    T get(T t);

    /**
     * All entities of the type
     * @param t the type
     * @return a list of zero or more elements
     */
    List<T> list(Class<T> t);

    /**
     * All entities of the type
     * @param t the type
     * @param limit The number of rows to return
     * @param offset The number of rows from the start of the result to
     *               begin returning values
     * @return a list of zero or more elements
     */
    List<T> list(Class<T> t, int limit, int offset);

    /**
     * Deletes and entity
     * @param t the instance of {@link Identifiable}
     */
    void remove(T t);

    /**
     * Updates an existing entity
     *
     * @param t An instance of {@link Identifiable}
     */
    void update(T t);

    /**
     * Tests whether an instance exists based on the implementation
     * @param t An instance of {@link Identifiable}
     * @return true if it exists
     */
    boolean exists(T t);

    /**
     * @return The number of T currently stored
     */
    long count(Class<T> t);
}
