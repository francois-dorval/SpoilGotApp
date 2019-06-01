package com.fdorval.spoilgot.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@Profile("default")
@Repository
public class FireBaseDaoImpl implements com.fdorval.spoilgot.dao.FireBaseDao {

	Logger LOG = LoggerFactory.getLogger(FireBaseDaoImpl.class);

	@Autowired
	DatabaseReference firebaseDatabase;

	@Override
	public List<GotCharacterFirebase> getCharacters() throws TechnicalException {
		List<GotCharacterFirebase> result = new ArrayList<GotCharacterFirebase>();

		DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
		
		//beurk
		final Semaphore semaphore = new Semaphore(0);
		ref.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				try {
				LOG.info("found {} characters", snapshot.child("characters").getChildrenCount());
				 String string = snapshot.toString();

				  for (DataSnapshot postSnapshot: snapshot.child("characters").getChildren()) {
					  GotCharacterFirebase character = postSnapshot.getValue(GotCharacterFirebase.class);
					LOG.info("--> " + character.toString());
				    result.add(character);
				  }
				}catch (Exception e) {
					LOG.error("erreur désérialisation", e);
				}finally {
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
