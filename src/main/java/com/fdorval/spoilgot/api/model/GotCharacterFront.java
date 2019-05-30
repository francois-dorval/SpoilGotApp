package com.fdorval.spoilgot.api.model;

/**
 * un classe qui représente l'objet personnage côté front
 * 
 * @author franc
 *
 */
public class GotCharacterFront {

	/**
	 *  name
	 */
	String name;
	
	/**
	 * meurt-il dans la saison courante?
	 */
	Boolean deadInSeason;
	
	/**
	 * cause de la mort
	 */
	String causeOfDeath;
	
	

	/**
	 * constructeur
	 */
	public GotCharacterFront() {
		
	}
	
	/**
	 * constructeur
	 * @param name
	 * @param deadInSeason
	 * @param causeOfDeath
	 */
	public GotCharacterFront(String name, Boolean deadInSeason, String causeOfDeath) {
		super();
		this.name = name;
		this.deadInSeason = deadInSeason;
		this.causeOfDeath = causeOfDeath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDeadInSeason() {
		return deadInSeason;
	}

	public void setDeadInSeason(Boolean deadInSeason) {
		this.deadInSeason = deadInSeason;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	@Override
	public String toString() {
		return "GotCharacterFront [name=" + name + ", deadInSeason=" + deadInSeason + ", causeOfDeath=" + causeOfDeath
				+ "]";
	}
	
	
	
}
