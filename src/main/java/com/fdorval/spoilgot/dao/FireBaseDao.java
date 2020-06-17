package com.fdorval.spoilgot.dao;

import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.util.exception.TechnicalException;

import java.util.List;


public interface FireBaseDao {

    /**
     * get the list of characters
     *
     * @return
     * @throws TechnicalException
     */
    List<GotCharacterBack> getCharacters() throws TechnicalException;

}
