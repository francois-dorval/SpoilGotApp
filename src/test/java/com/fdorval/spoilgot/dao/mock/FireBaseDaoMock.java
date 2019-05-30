package com.fdorval.spoilgot.dao.mock;


import java.util.List;

import org.mockito.Mockito;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;

@Profile("test & mock")
@Primary
@Repository
public class FireBaseDaoMock implements FireBaseDao {

	
    private FireBaseDao mockDelegate = Mockito.mock(FireBaseDao.class);

	@Override
	public List<GotCharacterFirebase> getCharacters() throws TechnicalException {
		return mockDelegate.getCharacters();


	}

	public FireBaseDao getMockDelegate() {
		return mockDelegate;
	}

}
