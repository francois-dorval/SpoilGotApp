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
public class BocdemoUnitTestsMock {


	@Autowired
    private FireBaseDao fireBaseDao;
	
	Logger LOG = LoggerFactory.getLogger(BocdemoUnitTestsMock.class);

	@Autowired
	SpoilBusiness bocBusiness;
	
	
	@Test
	public void testMethodesUtils() {
		GotCharacterFirebase perso = new GotCharacterFirebase("Perso qui se fait tuer dans la saison 2", 1, Season.S2, 2);
		
		Assert.assertTrue(bocBusiness.characterIsKilledBeforeSeason(perso, Season.S3));
		
		Assert.assertFalse(bocBusiness.characterIsKilledBeforeSeason(perso, Season.S2));
		
		Assert.assertTrue(bocBusiness.characterIsKilledInSeason(perso, Season.S2));


	}
	
//
//	@Test
//	public void testGetSudents() {
//		List<GotCharacterFront> result = new ArrayList<GotCharacterFront>();
//		result.add(new GotCharacterFront("Harry", 1));
//		result.add(new GotCharacterFront("Hermione", 2));
//		result.add(new GotCharacterFront("Voldemort", 3));
//        try {
//        	FireBaseDaoMock fireBaseDaoMock = (FireBaseDaoMock) fireBaseDao;
//
//			Mockito.when(fireBaseDaoMock.getMockDelegate(). getCharacters()).thenReturn(result);
//	
//		
//			List<GotCharacterFirebase> students = bocBusiness.getCharacters(1);
//			for (GotCharacterFirebase student : students) {
//				LOG.info("-> " + student);
//			}
//			Assert.assertEquals(students.get(2).getName(), "Voldemort");
//
//		} catch (Exception e) {
//			Assert.fail();
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
	
	
	

}
