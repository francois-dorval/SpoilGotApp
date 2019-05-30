package com.fdorval.spoilgot.dao.stub;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;

@Profile("test")
@Repository
public class FireBaseDaoStub implements FireBaseDao {

	Logger LOG = LoggerFactory.getLogger(FireBaseDaoStub.class);


	@Override
	public
	List<GotCharacterFirebase> getCharacters() throws TechnicalException{
		List<GotCharacterFirebase> result = new ArrayList<GotCharacterFirebase>();
		result.add(new GotCharacterFirebase("Dark", 1));
		result.add(new GotCharacterFirebase("Luke", 2));
		result.add(new GotCharacterFirebase("R2", 3));
		return result;


	}

}
