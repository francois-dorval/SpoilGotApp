package com.fdorval.spoilgot.dao.stub;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.dao.model.Season;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("test")
@Repository
public class StubDaoImpl implements FireBaseDao {

    Logger LOG = LoggerFactory.getLogger(StubDaoImpl.class);


    @Override
    public List<GotCharacterBack> getCharacters() throws TechnicalException {
        List<GotCharacterBack> result = new ArrayList<GotCharacterBack>();
        result.add(new GotCharacterBack(1, "Michel Stark", Season.S2, 2));
        result.add(new GotCharacterBack(2, "Jean-Luc Lannister"));
        result.add(new GotCharacterBack(3, "Un Zombie"));
        return result;


    }

}
