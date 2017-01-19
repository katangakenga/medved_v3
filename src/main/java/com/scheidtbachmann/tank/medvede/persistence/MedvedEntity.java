package com.scheidtbachmann.tank.medvede.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lorman.erich on 23. 6. 2015.
 */
@Entity
@Table(name = "medvede")
public class MedvedEntity extends AbstractEntity {

    @Column(name = "MENO", nullable = true)
    private String meno;

    public String getMeno() {
        return meno;
    }

    public void setMeno(final String meno) {
        this.meno = meno;
    }

}
