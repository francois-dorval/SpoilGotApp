package com.fdorval.spoilgot.dao.stub;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.model.GotCharacter;
import com.fdorval.spoilgot.util.exception.TechnicalException;

@Profile("test")
@Repository
public class FireBaseDaoStub implements FireBaseDao {

	Logger LOG = LoggerFactory.getLogger(FireBaseDaoStub.class);


	@Override
	public
	List<GotCharacter> getCharacters() throws TechnicalException{
		List<GotCharacter> result = new ArrayList<GotCharacter>();
		result.add(new GotCharacter("Dark", "Vador", 8));
		result.add(new GotCharacter("Luke", "Skywalker", 18));
		result.add(new GotCharacter("R2", "D2", 8));
		return result;


	}

}
