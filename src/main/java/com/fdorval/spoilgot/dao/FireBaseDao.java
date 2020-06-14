package com.fdorval.spoilgot.dao;

import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;

import java.util.List;


public interface FireBaseDao {

    /**
     * get the list of characters
     *
     * @return
     * @throws TechnicalException
     */
    List<GotCharacterFirebase> getCharacters() throws TechnicalException;

}
