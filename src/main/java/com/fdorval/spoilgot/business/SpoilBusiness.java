package com.fdorval.spoilgot.business;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.dao.model.Season;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * là où est le métier
 *
 * @author franc
 */
@Service
public class SpoilBusiness {

    Logger LOG = LoggerFactory.getLogger(SpoilBusiness.class);

    @Autowired
    FireBaseDao fireBaseDao;


    /**
     * la liste des personages d'une saison
     *
     * @param season : la saison demandée
     * @return
     * @throws TechnicalException
     */
    public List<GotCharacterFront> getCharactersInSeason(Season season) throws TechnicalException {
        List<GotCharacterFront> result = new ArrayList<>();

        List<GotCharacterBack> charactersFirebase = fireBaseDao.getCharacters();

        for (GotCharacterBack characterFirebase : charactersFirebase) {

            //si le personnage est mort avant la saison courante, on ne l'affiche pas
            if (!characterIsKilledBeforeSeason(characterFirebase, season)) {

                //on transforme l'objet firebase en objet "front"
                GotCharacterFront characterFront = convertToCharacterFront(characterFirebase, season);
                result.add(characterFront);
            }
        }
        return result;
    }


    /**
     * true si le personnage se fait tuer AVANT la saison en cours
     *
     * @param gotCharacterBack
     * @param season
     * @return
     */
    boolean characterIsKilledBeforeSeason(GotCharacterBack gotCharacterBack, Season season) {
        return gotCharacterBack.getKilledinseason() != null
                && gotCharacterBack.getKilledinseason().getValue() < season.getValue();
    }


    /**
     * true si le personnage se fait tuer PENDANT la saison en cours
     *
     * @param gotCharacterBack
     * @param season
     * @return
     */
    boolean characterIsKilledInSeason(GotCharacterBack gotCharacterBack, Season season) {
        return gotCharacterBack.getKilledinseason() != null
                && gotCharacterBack.getKilledinseason().getValue() == season.getValue();
    }


    /**
     * retourne un personnage en fonction de son id
     *
     * @param id
     * @return
     * @throws TechnicalException
     */
    GotCharacterBack findCharacteByID(Integer id) throws TechnicalException {
        List<GotCharacterBack> charactersFirebase = fireBaseDao.getCharacters();

        Optional<GotCharacterBack> found = charactersFirebase.stream().filter(x -> x.getId().equals(id)).findAny();
        if (found.isPresent()) {
            return found.get();
        }
        LOG.warn("c'est pas normal... " + id);
        return null;
    }


    /**
     * conversion personnage firebase -> personnages front
     *
     * @param gotCharacterBack
     * @param currentSeason
     * @return
     * @throws TechnicalException
     */
    public GotCharacterFront convertToCharacterFront(GotCharacterBack gotCharacterBack, Season currentSeason) throws TechnicalException {
        GotCharacterFront result = new GotCharacterFront();
        //le nom
        result.setName(gotCharacterBack.getName());

        //se fait-il tuer dans la saison en cours?
        boolean deadInSeason = characterIsKilledInSeason(gotCharacterBack, currentSeason);
        result.setDeadInSeason(deadInSeason);

        //cause de la mort
        if (deadInSeason) {
            result.setCauseOfDeath("killed by " + findCharacteByID(gotCharacterBack.getKilledby()).getName());
        }
        return result;


    }


}