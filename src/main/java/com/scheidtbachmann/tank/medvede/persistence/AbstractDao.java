package com.scheidtbachmann.tank.medvede.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * AbstractDao - manages basic functionality of each inherited DAO.
 * <p>
 * Created by lorman.erich on 21. 10. 2014.
 *
 * @param <T> persisted class as T
 */

public abstract class AbstractDao<T> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    /**
     * Dao.
     */
    private final Class<T> persistentClass;


    /**
     * Injected em from em producer.
     */
    @Inject
    protected EntityManager em;

    /**
     * Class constructor.
     *
     * @param t Class of the Entity bean. This could be retrieved via Reflection but there is some problem with
     *          Spring byte code manipulation
     *          so we decided to add it via DAO constructor.
     */
    protected AbstractDao(final Class<T> t) {
        this.persistentClass = t;
    }

    /**
     * Loads all items.
     *
     * @return list of all items.
     */
    public List<T> findAll() {
        String query = "from " + persistentClass.getName();
        StringBuffer sb = new StringBuffer();
        sb.append(query);

        String defaultOrderBy = getDefaultOrderBy();
        if (defaultOrderBy != null) {
            sb.append(" order by ");
            sb.append(defaultOrderBy);
        }

        logger.debug("Executing query '" + sb.toString() + "'");
        return em.createQuery(sb.toString()).getResultList();
    }

    /**
     * Updates entity to database.
     *
     * @param entity entity to be updated in database
     */
    public void update(final T entity) {
        logger.debug("Updating entity " + entity);
        em.merge(entity);

        logger.debug("Merged...");
    }

    /**
     * Creates new entity.
     *
     * @param entity entity to be persisted
     * @return returns new instance.
     */
    public T save(final T entity) {
        logger.debug("Persisting entity: " + entity);
        em.persist(entity);
        logger.debug("Returning: " + entity);
        return entity;
    }

    /**
     * Removes entity from the database.
     *
     * @param entity entity that is removed from database.
     */
    public void delete(final T entity) {
        logger.debug("Removing: " + entity);
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        logger.debug("Removed: " + entity);
    }

    /**
     * Loads one instance of the class {@link T} by id.
     *
     * @param id id of the instance
     * @return instance of the class from persistence storage.
     */
    public T findById(final Long id) {
        logger.debug("findById(" + id + ") called, persitence class is " + persistentClass + "...");
        return em.find(persistentClass, id);
    }

    /**
     * Stores all items to database.
     *
     * @param list list to be stored.
     * @return list with primary keys assigned
     */
    public List<T> saveAll(final List<T> list) {
        List<T> newList = new ArrayList();
        for (T ug : list) {
            newList.add(save(ug));
        }
        return newList;
    }

    /**
     * Persists all instances.
     *
     * @param data list of entities being persisted
     */
    public void updateAll(final List<T> data) {
        for (T t : data) {
            update(t);
        }
    }

    /**
     * Should return default order by for method {@link #findAll()}. Can return null.
     *
     * @return properties for sorting or null in case no sort required.
     */
    protected abstract String getDefaultOrderBy();
}
