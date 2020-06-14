package com.fdorval.spoilgot.dao.impl;

import com.fdorval.spoilgot.dao.model.GotCharacterFile;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.util.exception.TechnicalException;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

@Profile("default")
@Repository
public class FileDaoImpl implements com.fdorval.spoilgot.dao.FileDao {

	Logger LOG = LoggerFactory.getLogger(FileDaoImpl.class);

	@Autowired
	DatabaseReference firebaseDatabase;

	@Override
	public List<GotCharacterFile> getCharacters() throws TechnicalException {
		List<GotCharacterFirebase> result = new ArrayList<GotCharacterFirebase>();

		try {
			String content = null;

				content = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader()
						.getResource("characters.json")
						.toURI())));

				Gson gson = new Gson();

				GotCharacterFile[] array = gson.fromJson(content, GotCharacterFile[].class);
				return Arrays.asList(array);

			} catch (Exception e) {
				e.printStackTrace();
			}

return null;
		}
	}
