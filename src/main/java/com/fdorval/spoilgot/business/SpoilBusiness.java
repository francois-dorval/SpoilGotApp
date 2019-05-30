package com.fdorval.spoilgot.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.dao.model.Season;
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
    public List<GotCharacterFront> getCharacters(Season season) throws TechnicalException {
		List<GotCharacterFirebase> charactersFirebase = fireBaseDao.getCharacters();
		
		
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
	 * @param season.getVue()
	 * @return
	 */
	boolean characterIsKilledBeforeSeason(GotCharacterFirebase gotCharacterFirebase, Season season) {
		
		return     gotCharacterFirebase.getKilledinseason()!=null 
				&& gotCharacterFirebase.getKilledinseason().getValue() < season.getValue();
	}

	
	/**
	 * true si le personnage se fait tuer dans la saison en cours
	 * @param gotCharacterFirebase
	 * @param season
	 * @return
	 */
	boolean characterIsKilledInSeason(GotCharacterFirebase gotCharacterFirebase, Season season) {
		return  	gotCharacterFirebase.getKilledinseason()!=null 
				&& 	gotCharacterFirebase.getKilledinseason().getValue() == season.getValue();
	}



	/**
	 * conversion personnage firebase -> personnages front
	 * @param gotCharacterFirebase
	 * @return
	 */
	public GotCharacterFront map(GotCharacterFirebase gotCharacterFirebase, Season currentSeason ) {
		GotCharacterFront result = new GotCharacterFront();
		//le nom
		result.setName(gotCharacterFirebase.getName());
		
		//se fait-il tuer dans la saison en cours?
		boolean deadInSeasoon = characterIsKilledInSeason(gotCharacterFirebase, currentSeason); 
		result.setDeadInSeason(deadInSeasoon);
		
		//cause de la mort
		if (deadInSeasoon) {
			result.setCauseOfDeath("killed by "+gotCharacterFirebase.getKilledby());
		}
		
		return result;
	}

   
}