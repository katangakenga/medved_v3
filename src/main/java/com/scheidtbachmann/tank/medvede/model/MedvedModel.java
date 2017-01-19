package com.scheidtbachmann.tank.medvede.model;

import com.scheidtbachmann.tank.medvede.persistence.MedvedEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lorman.erich on 9. 4. 2015.
 */
@SessionScoped
@Named
public class MedvedModel implements Serializable {

    private MedvedEntity oznacenyMedved;

    private List<MedvedEntity> medvede;

    public void setMedvede(List<MedvedEntity> medvede) {
        this.medvede = medvede;
    }

    public List<MedvedEntity> getMedvede() {
        return medvede;
    }

    public MedvedEntity getOznacenyMedved() {
        return oznacenyMedved;
    }

    public void setOznacenyMedved(MedvedEntity oznacenyMedved) {
        this.oznacenyMedved = oznacenyMedved;
    }

}
