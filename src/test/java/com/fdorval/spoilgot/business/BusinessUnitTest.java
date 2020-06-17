package com.fdorval.spoilgot.business;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.dao.FireBaseDao;
import com.fdorval.spoilgot.dao.mock.FireBaseDaoMock;
import com.fdorval.spoilgot.dao.model.GotCharacterBack;
import com.fdorval.spoilgot.dao.model.Season;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * tests unitaires mockés : les données sont injectées à chaque test
 * <p>
 * Mock : données dynamiques
 *
 * @author françois
 */
@RunWith(SpringRunner.class)
@ActiveProfiles({"test", "mock"})
@ContextConfiguration(classes = {SpoilBusiness.class, FireBaseDaoMock.class})
public class BusinessUnitTest {


    @MockBean
    private FireBaseDao fireBaseDao;

    Logger LOG = LoggerFactory.getLogger(BusinessUnitTest.class);

    @Autowired
    SpoilBusiness spoilBusiness;


    /**
     * teste les méthodes utilisaires
     */
    @Test
    public void testMethodesUtils() {
        GotCharacterBack perso = new GotCharacterBack(1, "Perso qui se fait tuer dans la saison 2", Season.S2, 2);

        Assert.assertTrue(spoilBusiness.characterIsKilledBeforeSeason(perso, Season.S3));

        Assert.assertFalse(spoilBusiness.characterIsKilledBeforeSeason(perso, Season.S2));

        Assert.assertTrue(spoilBusiness.characterIsKilledInSeason(perso, Season.S2));


    }


    /**
     * tests basique : tous les personnages sont renvoyés
     */
    @Test
    public void shouldReturnAllCharacters() {
        List<GotCharacterBack> result = new ArrayList<>();
        result.add(new GotCharacterBack(1, "Jimmy Stark", Season.S3, 2));
        result.add(new GotCharacterBack(2, "Johnny Lannister"));
        try {

            Mockito.when(fireBaseDao.getCharacters()).thenReturn(result);

            //à la saison 1 on doit avoir 2 personnages
            List<GotCharacterFront> charactersS1 = spoilBusiness.getCharactersInSeason(Season.S1);
            Assert.assertEquals(charactersS1.size(), 2);

        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }


    /**
     * tests basique : un des deux personnages est filtré
     */
    @Test
    public void shouldReturnCharactersWhoAreNotDeadInSeason() {
        List<GotCharacterBack> result = new ArrayList<>();
        result.add(new GotCharacterBack(1, "Jimmy Stark", Season.S3, 2));
        result.add(new GotCharacterBack(2, "Johnny Lannister"));
        try {

            Mockito.when(fireBaseDao.getCharacters()).thenReturn(result);
            //à la saison 7 on doit avoir 1 personnage (Jimmy est mort)
            List<GotCharacterFront> charactersS7 = spoilBusiness.getCharactersInSeason(Season.S7);
            Assert.assertEquals(charactersS7.size(), 1);
            Assert.assertEquals(charactersS7.get(0).getName(), "Johnny Lannister");


        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }


    /**
     * Explication du problème : ...
     * Test qui le met en évidence à écrire...
     */
    @Test
    //TODO
    public void testSaison6Erreur500() {

    }


}
