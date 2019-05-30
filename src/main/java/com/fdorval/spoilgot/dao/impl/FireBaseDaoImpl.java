package com.fdorval.spoilgot.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.model.GotCharacter;
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
	public List<GotCharacter> getCharacters() throws TechnicalException {
		List<GotCharacter> result = new ArrayList<GotCharacter>();

		DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
		
		//beurk
		final Semaphore semaphore = new Semaphore(0);

		ref.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				LOG.info("found {} characters", snapshot.child("characters").getChildrenCount());
				 String string = snapshot.toString();

				  for (DataSnapshot postSnapshot: snapshot.child("characters").getChildren()) {
					  GotCharacter character = postSnapshot.getValue(GotCharacter.class);
					LOG.info("--> " + character.toString());

				    result.add(character);
				  }
				 LOG.debug(string);
		        semaphore.release();

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
