package com.scheidtbachmann.tank.medvede.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;


/**
 * Implementation of the Entity Manager Producer for acceptance point API.
 */
@ApplicationScoped
public class EntityManagerProducer {

    // name of the persistence unit as defined in persistence.xml
    private static final String PERSISTENCE_UNIT_ID = "medved";

    @PersistenceUnit(unitName = PERSISTENCE_UNIT_ID)
    private EntityManagerFactory factory;

    /**
     * Returns the persistent unit of the Internal Operator Portal. This method is applied using the standard annotation
     * {@link javax.persistence.PersistenceUnit} without any further stereotypes for injection.
     */
    @Produces
    @ApplicationScoped
    public EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_ID);
    }

    public void close(@Disposes final EntityManagerFactory entityManagerFactory) {   // SUPPRESS CHECKSTYLE
        entityManagerFactory.close();
    }

    /**
     * Returns the persistent context of the Internal Operator Portal. This method is applied using the standard
     * annotation
     * {@link javax.persistence.PersistenceContext} without any further stereotypes for injection.
     */
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) { // SUPPRESS CHECKSTYLE
        if (entityManager != null) {
            entityManager.close();
        }
    }

}
