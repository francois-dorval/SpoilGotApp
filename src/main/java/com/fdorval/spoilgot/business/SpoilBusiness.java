package com.fdorval.spoilgot.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import com.fdorval.spoilgot.model.GotCharacter;

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
    public List<GotCharacter> getCharacters(Integer season) throws TechnicalException {
		List<GotCharacter> result = fireBaseDao.getCharacters();
		if (season >1) {
			throw new TechnicalException("Erreur : filtre non implémenté!");
		}
		//ici filter les students dont la note est <10
		
		return result;
    }

   
}