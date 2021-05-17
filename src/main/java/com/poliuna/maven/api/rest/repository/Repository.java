package com.poliuna.maven.api.rest.repository;
import java.util.Collection;


/**
 *
 * @author Sourabh Sharma
 * @param <TE>
 * @param <T>
 */
public interface Repository<TE, T> {

    /**
     *
     * @param entity
     */
    void add(TE entity);

    /**
     *
     * @param id
     */
    void remove(T id);

    /**
     *
     * @param entity
     */
    void update(TE entity);
    
    /**
     *
     * @param id
     * @return
     */
    boolean contains(T id);

    /**
     *
     * @param id
     * @return
     */
    TE get(T id);

    /**
     *
     * @return
     */
    Collection<TE> getAll();
    
    boolean containsNameId(String name, T id);
    
    Collection<TE> findByNameId(String name, T id);
}
