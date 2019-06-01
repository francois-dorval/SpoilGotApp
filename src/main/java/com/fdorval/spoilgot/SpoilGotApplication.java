package com.fdorval.spoilgot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpoilGotApplication {

	/**
	 * launch spring
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpoilGotApplication.class, args);
	}

}
