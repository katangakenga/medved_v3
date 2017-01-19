package com.scheidtbachmann.tank.medvede.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

/**
 * Created by lorman.erich on 23. 6. 2015.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "medvedSeq", sequenceName = "medvedSeq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medvedSeq")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
