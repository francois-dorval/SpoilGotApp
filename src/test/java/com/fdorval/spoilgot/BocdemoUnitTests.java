package com.fdorval.spoilgot;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.business.SpoilBusiness;
import com.fdorval.spoilgot.dao.model.Season;
import com.fdorval.spoilgot.dao.stub.FireBaseDaoStub;


/**
 * tests unitaires bouchonnés : les données viennent de 
 * com.fdorval.bocdemo.dao.stub.FireBaseDaoStub
 * 
 * Stub : données statiques
 * @author françois
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {SpoilBusiness.class, FireBaseDaoStub.class})
public class BocdemoUnitTests {

	
	
	
	Logger LOG = LoggerFactory.getLogger(BocdemoUnitTests.class);

	@Autowired
	SpoilBusiness spoilBusiness;
	
	

	@Test
	public void testGetSudents() {
		try {
			List<GotCharacterFront> persos = spoilBusiness.getCharacters(Season.S1);
			for (GotCharacterFront perso : persos) {
				LOG.info("-> " + perso);
			}
			Assert.assertEquals(persos.get(1).getName(), "Luke");

		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	
	

}
