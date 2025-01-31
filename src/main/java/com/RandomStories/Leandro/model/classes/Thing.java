package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;

public abstract class Thing {
    protected String name;
    protected Gender gender;
    protected String adjective;

    public Thing(String thingName, Gender thingGender, String adjective){
        this.name = thingName;
        this.gender = thingGender;
        this.adjective = adjective;
    }

    public Thing(String thingName, Gender thingGender){
        this.name = thingName;
        this.gender = thingGender;
        adjective = "";
    }

    public void setAdjective(String adj){
        adjective = adj;
    }

    public String toString(){
        return name;
    }

    public String getName(){
        return name;
    }

    public Gender getGender(){
        return gender;
    }

    public String getAdjective(){
        return adjective;
    }

    public abstract String getUnityPronoun();
    public abstract String getSingularPronoun();
    public abstract String getPluralPronoun();
    public abstract String getDistantPronoun();
}
