package com.fdorval.spoilgot.dao;

import java.util.List;

import com.fdorval.spoilgot.model.GotCharacter;
import com.fdorval.spoilgot.util.exception.TechnicalException;


public interface FireBaseDao {

	/**
	 * get the list of students
	 * @return
	 * @throws TechnicalException
	 */
	List<GotCharacter> getCharacters() throws TechnicalException;

}
