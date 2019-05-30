package com.fdorval.spoilgot.dao;

import java.util.List;

import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;


public interface FireBaseDao {

	/**
	 * get the list of characters
	 * @return
	 * @throws TechnicalException
	 */
	List<GotCharacterFirebase> getCharacters() throws TechnicalException;

}
