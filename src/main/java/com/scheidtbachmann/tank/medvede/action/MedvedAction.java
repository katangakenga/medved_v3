package com.scheidtbachmann.tank.medvede.action;

import com.scheidtbachmann.tank.medvede.model.MedvedModel;
import com.scheidtbachmann.tank.medvede.persistence.MedvedEntity;
import com.scheidtbachmann.tank.medvede.service.MedvedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lorman.erich on 9. 4. 2015.
 */
@ApplicationScoped
@Named
public class MedvedAction {

    private static final Logger logger = LoggerFactory.getLogger(MedvedAction.class);

    @Inject
    MedvedModel medvedModel;

    @Inject
    MedvedService medvedService;

    public void nacitajVsetkyMedvedeDoModelu() {
        medvedModel.setMedvede(medvedService.vratVsetkyNaozajstneMedvede());
        logger.debug("total : " + medvedModel.getMedvede().size());

        if (medvedModel.getMedvede().size() > 0) {
            medvedModel.setOznacenyMedved(medvedModel.getMedvede().get(0));
        } else {
            medvedModel.setOznacenyMedved(null);
        }

    }

    public void vytvorMedvedaANacitajVsetkyMedvedeDoModelu() {
        // vytvori medveda, zapise do DB
        MedvedEntity medvedEntity = new MedvedEntity();
        medvedEntity.setMeno("medved");
        medvedService.ulozMedveda(medvedEntity);
        logger.debug("Medved ulozeny, id =" + medvedEntity.getId());

        // nastavi medvedovi meno, updatne ho
        medvedEntity.setMeno("medved " + medvedEntity.getId());
        medvedService.ulozMedveda(medvedEntity);

        nacitajVsetkyMedvedeDoModelu();
    }

    public String ulozOznacenehoMedvedaAVratSa() {
        medvedService.ulozMedveda(medvedModel.getOznacenyMedved());
        return "zoznam";
    }
}
