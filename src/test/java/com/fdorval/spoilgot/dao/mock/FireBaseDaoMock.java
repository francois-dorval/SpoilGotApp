package com.fdorval.spoilgot.dao.mock;


import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import org.mockito.Mockito;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * classe utilitaire
 *
 * @author franc
 */
@Profile("test & mock")
@Primary
@Repository
public class FireBaseDaoMock implements FireBaseDao {


    private FireBaseDao mockDelegate = Mockito.mock(FireBaseDao.class);

    @Override
    public List<GotCharacterBack> getCharacters() throws TechnicalException {
        return mockDelegate.getCharacters();


    }

    public FireBaseDao getMockDelegate() {
        return mockDelegate;
    }

}
