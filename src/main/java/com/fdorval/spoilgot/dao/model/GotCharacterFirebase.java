package com.fdorval.spoilgot.dao.model;

/**
 * les données de la base firebase
 * @author franc
 *
 */
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKilledby() {
		return killedby;
	}

	public void setKilledby(Integer killedby) {
		this.killedby = killedby;
	}

	public Season getKilledinseason() {
		return killedinseason;
	}

	public void setKilledinseason(Season killedinseason) {
		this.killedinseason = killedinseason;
	}

	@Override
	public String toString() {
		return "GotCharacter [name=" + name + ", id=" + id + ", killedby=" + killedby + ", killedinseason="
				+ killedinseason + "]";
	}

	
	
	

}
