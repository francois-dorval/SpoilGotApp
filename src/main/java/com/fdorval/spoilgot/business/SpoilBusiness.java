package com.fdorval.spoilgot.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;

@Service
public class SpoilBusiness {

	@Autowired 
	FireBaseDao fireBaseDao;
	
	
	/**
	 * la liste des personages
	 * @param season : la saison demandée
	 * @return 
	 * @throws TechnicalException
	 */
    public List<GotCharacterFront> getCharacters(Integer season) throws TechnicalException {
		List<GotCharacterFirebase> charactersFirebase = fireBaseDao.getCharacters();
		if (season >1) {
		//	throw new TechnicalException("Erreur : filtre non implémenté!");
		}

		
		List<GotCharacterFront> result = new ArrayList<>();
		for (GotCharacterFirebase characterFirebase:charactersFirebase) {
			
			//si le personnage est mort avant la saison courante, on ne l'affiche pas
			if (! characterIsKilledBeforeSeason(characterFirebase, season)){
				
				//on transforme l'objet firebase en objet "front"
				GotCharacterFront characterFront = map(characterFirebase, season);
				result.add(characterFront);	
			}
		}
		return result;
    }


	/**
	 *  true si le personnage se fait tuer avant la saison en cours
	 * @param gotCharacterFirebase
	 * @param season
	 * @return
	 */
	boolean characterIsKilledBeforeSeason(GotCharacterFirebase gotCharacterFirebase, Integer season) {
		return  gotCharacterFirebase.getKilledinseason()!=null && gotCharacterFirebase.getKilledinseason().getValue() < season;

	}

	
	/**
	 * true si le personnage se fait tuer dans la saison en cours
	 * @param gotCharacterFirebase
	 * @param season
	 * @return
	 */
	boolean characterIsKilledInSeason(GotCharacterFirebase gotCharacterFirebase, Integer season) {
		return  gotCharacterFirebase.getKilledinseason()!=null && gotCharacterFirebase.getKilledinseason().getValue() == season;
	}



	/**
	 * conversion personnage firebase -> personnages front
	 * @param gotCharacterFirebase
	 * @return
	 */
	public GotCharacterFront map(GotCharacterFirebase gotCharacterFirebase, Integer currentSeason ) {
		GotCharacterFront result = new GotCharacterFront();
		//le nom
		result.setName(gotCharacterFirebase.getName());
		
		//se fait-i tuer dans la saison en cours?
		boolean deadInSeasoon = characterIsKilledInSeason(gotCharacterFirebase, currentSeason); 
		
		result.setDeadInSeason(deadInSeasoon);
		if (deadInSeasoon) {
			result.setCauseOfDeath("killed by "+gotCharacterFirebase.getKilledby());
		}
		
		return result;
	}

   
}