package com.sapient.sadp.utility;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

/**
 * The Interface DAO.
 *
 * @param <E>
 *            the element type
 */
public interface DAO<E> {
    @PersistenceContext
    public EntityManager entityManager=null;

    /**
     * Persist.
     *
     * @param entity
     *            the entity
     */
    @Transactional
    public void persist(E entity);

    /**
     * Removes the.
     *
     * @param entity
     *            the entity
     */
    public void remove(long entityId);

    /**
     * Find by id.
     *
     * @param id
     *            the id
     * @return the e
     */
    public E findById(long id);
}
