package com.walterj.crusty.dao.impl;

import com.walterj.crusty.dao.IdentifiableEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Provides a base class for classes that represent an entity in a database
 * that all have a uniform numeric identifier and work with my DAO framework
 *
 * @author Walter Jordan
 */
@MappedSuperclass
public abstract class IdentifiableEntityImpl
    implements IdentifiableEntity, Serializable {

    private long id = -1L;
    private String name;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    @NaturalId(mutable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiableEntityImpl that = (IdentifiableEntityImpl) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override public String toString() {
        return "IdentifiableEntityImpl{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
