package com.fdorval.spoilgot.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.business.SpoilBusiness;
import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.mock.FireBaseDaoMock;
import com.fdorval.spoilgot.dao.model.GotCharacterFirebase;
import com.fdorval.spoilgot.dao.model.Season;


/**
 * tests unitaires mockés : les données sont injectées à chaque test
 * 
 * Mock : données dynamiques
 * 
 * @author françois
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles({"test", "mock"})
@ContextConfiguration(classes = {SpoilBusiness.class, FireBaseDaoMock.class})
public class BusinessUnitTestsMock {


	@Autowired
    private FireBaseDao fireBaseDao;
	
	Logger LOG = LoggerFactory.getLogger(BusinessUnitTestsMock.class);

	@Autowired
	SpoilBusiness spoilBusiness;
	
	
	/**
	 * teste les méthodes utilisaires
	 */
	@Test
	public void testMethodesUtils() {
		GotCharacterFirebase perso = new GotCharacterFirebase(1, "Perso qui se fait tuer dans la saison 2", Season.S2, 2);
		
		Assert.assertTrue(spoilBusiness.characterIsKilledBeforeSeason(perso, Season.S3));
		
		Assert.assertFalse(spoilBusiness.characterIsKilledBeforeSeason(perso, Season.S2));
		
		Assert.assertTrue(spoilBusiness.characterIsKilledInSeason(perso, Season.S2));


	}
	

	@Test
	public void testGetAllCharacters() {
		List<GotCharacterFirebase> result = new ArrayList<>();
		result.add(new GotCharacterFirebase(1, "Jimmy Stark", Season.S3, 2));
		result.add(new GotCharacterFirebase(2, "Johnny Lannister"));
        try {
        	FireBaseDaoMock fireBaseDaoMock = (FireBaseDaoMock) fireBaseDao;

			Mockito.when(fireBaseDaoMock.getMockDelegate(). getCharacters()).thenReturn(result);
	
			//à la saison 1 on doit avoir 2 personnages
			List<GotCharacterFront> charactersS1 = spoilBusiness.getCharacters(Season.S1);
			Assert.assertEquals(charactersS1.size(), 2);

			List<GotCharacterFront> charactersS7 = spoilBusiness.getCharacters(Season.S7);
			Assert.assertEquals(charactersS7.size(), 1);

			
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	

	@Test
	public void testGetCharactersFilter() {
		List<GotCharacterFirebase> result = new ArrayList<>();
		result.add(new GotCharacterFirebase(1, "Jimmy Stark", Season.S3, 2));
		result.add(new GotCharacterFirebase(2, "Johnny Lannister"));
        try {
        	FireBaseDaoMock fireBaseDaoMock = (FireBaseDaoMock) fireBaseDao;

			Mockito.when(fireBaseDaoMock.getMockDelegate(). getCharacters()).thenReturn(result);
	
			//à la saison 7 on doit avoir 1 personnage (Jimmy est mort)
			List<GotCharacterFront> charactersS7 = spoilBusiness.getCharacters(Season.S7);
			Assert.assertEquals(charactersS7.size(), 1);
			Assert.assertEquals(charactersS7.get(0).getName(), "Johnny Lannister");

			
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	
	
	
	

}
