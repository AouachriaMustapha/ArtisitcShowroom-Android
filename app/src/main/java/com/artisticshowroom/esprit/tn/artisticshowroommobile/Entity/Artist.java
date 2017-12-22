package com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity;

import org.json.JSONObject;

/**
 * Created by mustapha- on 16/04/2017.
 */

public class Artist extends User {

    private int experience;
    private String description;

    public Artist(JSONObject j) {
        super(j);
        this.experience =j.optInt("experience");
        this.description=j.optString("description");

    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
