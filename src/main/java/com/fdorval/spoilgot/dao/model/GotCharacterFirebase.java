package com.fdorval.spoilgot.dao.model;

import lombok.Data;

/**
 * les données de la base firebase
 * @author franc
 *
 */
@Data

public class GotCharacterFirebase {
	
	/**
	 * name
	 */
	String name;
	
	/**
	 * id
	 */
	Integer id;
	
	/**
	 * tué par...
	 */
	Integer killedby;
	
	/**
	 * saison de la mort
	 */
	Season killedinseason;

	/**
	 * constructeur
	 */
	GotCharacterFirebase(){	
	}

	/**
	 * constructeur
	 * @param id
	 * @param name
	 */
	public GotCharacterFirebase(Integer id, String name) {
		this(id, name, null, null);
	}

		
	/**
	 * constructeur
	 * @param id
	 * @param name
	 * @param killedinseason
	 * @param killedby
	 */
	public GotCharacterFirebase(Integer id, String name, Season killedinseason, Integer killedby) {
		super();
		this.name = name;
		this.id = id;
		this.killedby = killedby;
		this.killedinseason = killedinseason;
	}



	
	

}
