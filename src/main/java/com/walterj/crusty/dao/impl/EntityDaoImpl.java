package com.walterj.crusty.dao.impl;

import com.walterj.crusty.dao.Dao;
import com.walterj.crusty.dao.Identifiable;
import com.walterj.crusty.dao.IdentifiableEntity;
import com.walterj.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;
import java.util.List;

/**
 * @author Walter Jordan
 */
public class EntityDaoImpl<T extends IdentifiableEntity> implements Dao<T> {

    private static final Logger LOG = LogManager.getLogger(EntityDaoImpl.class.getName());

    @Override public long add(T t) {
        if (t == null) {
            LOG.warn("add(): NULL value passed for update!");
            return -1;
        }
        LOG.debug("add(): " + t);

        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
        return t.getId();
    }

    /**
     * Look up an instance of {@link IdentifiableEntity} by Id.
     *
     * @param id The Id to look for {@link IdentifiableEntity} -> getId()
     * @return An instance of {@link Identifiable} or null.
     */
    @Override
    public T get(Class<T> type, long id) {
        LOG.debug("get(): called for Id: " + id);

        Session s = HibernateUtil.openSession();
        final IdentifierLoadAccess<T> byId = s.byId(type);
        final T load = byId.load(id);
        s.close();
        return load;
    }

    /**
     * Look up the stored instance using entity properties determined by
     * implementation. Can be used for natural key lookup.
     *
     * @param t An instance of {@link Identifiable}
     * @return An instance or null
     */
    @Override
    public T get(T t) {
        LOG.debug("get(): called for: " + t);
        Session s = HibernateUtil.openSession();
        final NaturalIdLoadAccess<? extends IdentifiableEntity> access = s.byNaturalId(t.getClass());
        final T load = (T) access.using("name", t.getName()).load();
        s.close();
        return load;
    }

    /**
     * Over-ride for lookup by natual key. The default implementation uses the name
     * identifier of the {@link Identifiable}
     * base class
     *
     * @param t An object of type T
     * @return true if it exists
     */
    public boolean exists(T t) {

        if (t == null) return false;
        LOG.debug("exists(): Called for [" + t.getClass().getName() + "]: "
            + t.toString());
        Session s = HibernateUtil.openSession();
        final NaturalIdLoadAccess<? extends Identifiable> loadAccess = s.byNaturalId(t.getClass());
        boolean exists = (loadAccess.using("name", t.getName()).load() != null);
        s.close();
        return exists;
    }

    @Override
    public List<T> list(Class<T> type) {

        LOG.debug("list(): Called for " + type.getName());
        Session s = HibernateUtil.openSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = s.createQuery(criteria).getResultList();
        s.close();
        return data;
    }

    @Override
    public List<T> list(Class<T> type, int limit, int offset) {

        LOG.debug("list(): Called for " + type.getName());
        Session s = HibernateUtil.openSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = s.createQuery(criteria)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
        s.close();
        return data;
    }

    @Override
    public long count(Class<T> type) {
        LOG.debug("count(): Called for " + type.getName());
        Session s = HibernateUtil.openSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count((criteria.from(type))));
        return s.createQuery(criteria).getSingleResult();
    }

    @Override
    public void remove(T t) {
        if (t == null) {
            LOG.warn("remove(): NULL value passed for update!");
            return;
        }
        LOG.debug("remove(): " + t);
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.delete(t);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(T t) {
        if (t == null) {
            LOG.warn("update(): NULL value passed for update!");
            return;
        }
        LOG.debug("update(): " + t);
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.saveOrUpdate(t);
        s.getTransaction().commit();
        s.close();
    }
}
