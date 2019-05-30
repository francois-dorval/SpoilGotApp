package com.fdorval.spoilgot;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.fdorval.spoilgot.api.model.GotCharacterFront;


/**
 * test d'intégration NON bouchonné : les données viennent de firebase
 * -> test dépendant des données -> instable
 * @author françois
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BocdemoIntegrationTests {

	Logger LOG = LoggerFactory.getLogger(BocdemoIntegrationTests.class);

	 @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    @Test
	    public void shouldReturnCharacters() throws Exception {
	    	GotCharacterFront[] persos  = this.restTemplate.getForObject("http://localhost:" + port + "/characters",
	    			GotCharacterFront[].class);
	    	assertThat(persos[0].getName().equals("Stark"));
	    }
	    
	    @Test
	    public void wrongUrlIn404() throws Exception {
	        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/kamoulox",
	                String.class).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	    }
}
