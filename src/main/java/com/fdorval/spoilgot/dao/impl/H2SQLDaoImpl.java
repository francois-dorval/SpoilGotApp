package com.fdorval.spoilgot.dao.impl;

import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("default")
@Repository
public class H2SQLDaoImpl implements com.fdorval.spoilgot.dao.FireBaseDao {

    Logger LOG = LoggerFactory.getLogger(FireBaseDaoImpl.class);

    @Autowired
    GotCharacterRepository repository;

    @Override
    public List<GotCharacterBack> getCharacters() throws TechnicalException {
        LOG.info("-->getCharacters");
        List<GotCharacterBack> result = new ArrayList<GotCharacterBack>();
        repository.findAll().forEach(charac -> result.add(charac));
        LOG.info("<-getCharacters");

        return result;
    }
}
