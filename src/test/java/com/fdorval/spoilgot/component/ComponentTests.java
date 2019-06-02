package com.fdorval.spoilgot.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdorval.spoilgot.api.model.GotCharacterFront;


/**
 * test de composant bouchonné : les données viennent de
 * FireBaseDaoStub
 * @author françois
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ComponentTests {

	Logger LOG = LoggerFactory.getLogger(ComponentTests.class);

	 @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    /**
	     * test basique : l'appli doit retourner une liste de personnage contenant un zombie
	     * @throws Exception
	     */
	    @Test
	    public void shouldReturnCharactersWithZombie() throws Exception {
	    	GotCharacterFront[] persos = this.restTemplate.getForObject("http://localhost:" + port + "/characters?season=4",
	    			GotCharacterFront[].class);
	    	boolean zombieFound = false;
	    	for (GotCharacterFront charac:persos) {
	    		if (charac.getName().contains("Zombie")) {
	    			zombieFound = true;
	    		}
	    	}
	    	Assert.assertTrue(zombieFound);
	    }
	    

	    /**
	     * l'appi doit retourner 404 en cas d'url erronnée
	     * @throws Exception
	     */
	    @Test
	    public void wrongUrlIn404() throws Exception {
	        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/kamoulox",
	                String.class).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	    }
	    
	    
	    /**
	     * l'appli doit retourner 400 si le paramètre "season" n'est pas un entier
	     * @throws Exception
	     */
	    //TODO
	    @Test
	    public void shouldReturn400ifSeasonIsNotInt() throws Exception {
	    	//A vous de coder
	    }
	    
	    /**
	     * l'appli doit retourner 400 si le paramètre "season" est <1
	     * @throws Exception
	     */
	    //TODO
	    @Test
	    public void shouldReturn400ifSeasonIsLowerThanOne() throws Exception {
	    	//A vous de coder
	    }
	    
	    /**
	     * l'appli doit retourner 400 si le paramètre "season" est >8
	     * @throws Exception
	     */
	    //TODO
	    @Test
	    public void shouldReturn400ifSeasonIsHigherThanEight() throws Exception {
	    	//A vous de coder
	    }
	    
}
