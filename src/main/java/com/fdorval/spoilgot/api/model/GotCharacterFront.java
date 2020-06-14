package com.fdorval.spoilgot.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * un classe qui représente l'objet personnage côté front
 *
 * @author franc
 */
@ApiModel(description = "Class representing a character.")

public class GotCharacterFront {

    /**
     * name
     */
    @ApiModelProperty(notes = "character name", example = "John Snow")
    String name;

    /**
     * meurt-il dans la saison courante?
     */
    @ApiModelProperty(notes = "does the character die in current season?")

    Boolean deadInSeason;

    /**
     * cause de la mort
     */
    @ApiModelProperty(notes = "who or what killed the character?", example = "a dragon")

    String causeOfDeath;


    /**
     * constructeur
     */
    public GotCharacterFront() {

    }

    /**
     * constructeur
     *
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
