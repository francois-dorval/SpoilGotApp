package com.fdorval.spoilgot.dao;

import com.fdorval.spoilgot.dao.model.GotCharacterFile;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;

import java.util.List;


public interface FileDao {

	/**
	 * get the list of characters
	 * @return
	 * @throws TechnicalException
	 */
	List<GotCharacterFile> getCharacters() throws TechnicalException;

}
