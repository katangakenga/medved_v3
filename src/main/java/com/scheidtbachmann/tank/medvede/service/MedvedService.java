package com.scheidtbachmann.tank.medvede.service;

import com.scheidtbachmann.tank.medvede.persistence.MedvedDao;
import com.scheidtbachmann.tank.medvede.persistence.MedvedEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by lorman.erich on 9. 4. 2015.
 */
@ApplicationScoped
public class MedvedService {

    @Inject
    MedvedDao medvedDao;

    public void ulozMedveda(MedvedEntity medvedEntity) {
        if (medvedEntity.getId() == null) {
            medvedDao.save(medvedEntity);
        } else {
            medvedDao.update(medvedEntity);
        }

    }

    public List<MedvedEntity> vratVsetkyNaozajstneMedvede() {
        return medvedDao.findAll();
    }

}
