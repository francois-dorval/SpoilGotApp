package com.fdorval.spoilgot.business;

import java.util.ArrayList;
import java.util.List;

import com.fdorval.spoilgot.dao.FileDao;
import com.fdorval.spoilgot.dao.model.GotCharacterFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.impl.FireBaseDaoImpl;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.dao.model.Season;
import com.fdorval.spoilgot.util.exception.TechnicalException;

/**
 * là où est le métier
 * @author franc
 *
 */
@Service
public class SpoilBusiness {

	Logger LOG = LoggerFactory.getLogger(SpoilBusiness.class);

	@Autowired
	FileDao fileDao;
	
	
	/**
	 * la liste des personages d'une saison
	 * @param season : la saison demandée
	 * @return 
	 * @throws TechnicalException
	 */
    public List<GotCharacterFile> getCharacters(Season season) throws TechnicalException {
    	List<GotCharacterFront> result = new ArrayList<>();

		List<GotCharacterFile> characters = fileDao.getCharacters();

//		for (GotCharacterFile characterFirebase:characters) {
//
//			//si le personnage est mort avant la saison courante, on ne l'affiche pas
//			if (! characterIsKilledBeforeSeason(characterFirebase, season)){
//
//				//on transforme l'objet firebase en objet "front"
//				GotCharacterFront characterFront = map(characterFirebase, season);
//				result.add(characterFront);
//			}
//		}
		return characters;
    }


//	/**
//	 *  true si le personnage se fait tuer AVANT la saison en cours
//	 * @param gotCharacterFirebase
//	 * @param season.getVue()
//	 * @return
//	 */
//	boolean characterIsKilledBeforeSeason(GotCharacterFirebase gotCharacterFirebase, Season season) {
//
//		return     gotCharacterFirebase.getKilledinseason()!=null
//				&& gotCharacterFirebase.getKilledinseason().getValue() < season.getValue();
//	}
//
//
//	/**
//	 * true si le personnage se fait tuer PENDANT la saison en cours
//	 * @param gotCharacterFirebase
//	 * @param season
//	 * @return
//	 */
//	boolean characterIsKilledInSeason(GotCharacterFirebase gotCharacterFirebase, Season season) {
//		return  	gotCharacterFirebase.getKilledinseason()!=null
//				&& 	gotCharacterFirebase.getKilledinseason().getValue() == season.getValue();
//	}
//
//
//	/**
//	 * retourne un personnage en fonction de son id
//	 * @param allCharactersFirebase
//	 * @param id
//	 * @return
//	 * @throws TechnicalException
//	 */
//	GotCharacterFirebase findCharacteByID( Integer id) throws TechnicalException {
//    	List<GotCharacterFirebase> charactersFirebase = fireBaseDao.getCharacters();
//		for(GotCharacterFirebase character:charactersFirebase) {
//			if (character.getId().equals(id)) {
//				return character;
//			}
//		}
//		LOG.warn("c'est pas normal... " +id);
//		return null;
//	}
//
//
//
//	/**
//	 * conversion personnage firebase -> personnages front
//	 * @param gotCharacterFirebase
//	 * @param charactersFirebase
//	 * @return
//	 * @throws TechnicalException
//	 */
//	public GotCharacterFront map(GotCharacterFirebase gotCharacterFirebase, Season currentSeason) throws TechnicalException {
//		GotCharacterFront result = new GotCharacterFront();
//		//le nom
//		result.setName(gotCharacterFirebase.getName());
//
//		//se fait-il tuer dans la saison en cours?
//		boolean deadInSeason = characterIsKilledInSeason(gotCharacterFirebase, currentSeason);
//		result.setDeadInSeason(deadInSeason);
//
//		//cause de la mort
//		if (deadInSeason) {
//			result.setCauseOfDeath("killed by "+findCharacteByID(gotCharacterFirebase.getKilledby()).getName());
//		}
//		return result;
//
//
//	}

   
}