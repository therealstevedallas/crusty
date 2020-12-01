package com.walterj.crusty.model;

import com.walterj.crusty.dao.impl.IdentifiableEntityImpl;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;

/**
 * @author Walter Jordan
 */
@Entity
@Table(name = "account",
    indexes = {@Index(unique = true, columnList = "name", name = "idx_account")})
public class Account extends IdentifiableEntityImpl {

    private boolean active;
    private String password;
    private String lastName;
    private String firstName;
    private Timestamp created;
    private Timestamp lastLogin;

    public Account() {

        Timestamp t = new Timestamp(System.currentTimeMillis());
        created = t;
        lastLogin = t;
        active = true;
    }

    @Type(type = "yes_no")
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Column(name = "last_login", nullable = false)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "passwd", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient public String getDisplayName() {

        String r;
        if (lastName == null || "".equals(lastName)) {
            r = getName();
        }
        else {
            r = lastName + ", " + firstName;
        }
        return r;
    }

    @Override public String toString() {
        return "User{ id=" + getId()
                 + ", name=" + getName()
                 + ", active=" + active
                 + ", created=" + created
                 + ", lastLogin=" + lastLogin
                 +'}';
    }
}
