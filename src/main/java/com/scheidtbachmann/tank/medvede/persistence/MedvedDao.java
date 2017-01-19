package com.scheidtbachmann.tank.medvede.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lorman.erich on 23. 6. 2015.
 */
@ApplicationScoped
@Transactional
public class MedvedDao extends AbstractDao<MedvedEntity> {

    public MedvedDao() {
        super(MedvedEntity.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return null;
    }

}
