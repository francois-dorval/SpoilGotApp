package com.fdorval.spoilgot.dao.impl;

import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import com.google.firebase.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

@Profile("disabled")
@Repository
public class FireBaseDaoImpl implements com.fdorval.spoilgot.dao.FireBaseDao {

    Logger LOG = LoggerFactory.getLogger(FireBaseDaoImpl.class);

    @Autowired
    DatabaseReference firebaseDatabase;

    @Override
    @Cacheable("characters")
    public List<GotCharacterBack> getCharacters() throws TechnicalException {
        List<GotCharacterBack> result = new ArrayList<GotCharacterBack>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        //beurk
        final Semaphore semaphore = new Semaphore(0);
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                try {
                    LOG.info("found {} characters", snapshot.child("characters").getChildrenCount());
                    String string = snapshot.toString();

                    for (DataSnapshot postSnapshot : snapshot.child("characters").getChildren()) {
                        GotCharacterBack character = postSnapshot.getValue(GotCharacterBack.class);
                        LOG.info("--> " + character.toString());
                        result.add(character);
                    }
                } catch (Exception e) {
                    LOG.error("erreur désérialisation", e);
                } finally {
                    semaphore.release();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                LOG.error("appel firebase ko", error);
                semaphore.release();


            }
        });

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            LOG.error("aarg", e);
            TechnicalException.throwTechnicalException("aaaarg", e);
        }
        return result;

    }

}
