package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;
import com.RandomStories.Leandro.model.exceptions.NullValueException;

public abstract class Thing {
    protected String name;
    protected Gender gender;
    protected Adjective adjective;

    public Thing(String thingName, Gender thingGender, Adjective adjective){
        this.name = thingName;
        this.gender = thingGender;
        this.adjective = adjective;
    }

    public Thing(String thingName, Gender thingGender){
        this.name = thingName;
        this.gender = thingGender;
        adjective = null;
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

    public String getAdjectiveString(){
        if(this.adjective == null){
            throw new NullValueException("Se intentó obtener un adjetivo antes de que este sea inicializado. Utilice setAdjective() primero.");
        }
        return adjective.toString();
    }

    public Adjective getAdjective(){
        if(this.adjective == null){
            throw new NullValueException("Se intentó obtener un adjetivo antes de que este sea inicializado. Utilice setAdjective() primero.");
        }
        return adjective;
    }

    public void setAdjective(Adjective adj){
        checkGenderCompatibility(adj);
        this.adjective = adj;
    }

    private void checkGenderCompatibility(Adjective adj)throws IllegalArgumentException {
        if(adj.getGender() != this.gender){
            throw new IllegalArgumentException("No se puede aplicar un adjetivo de género "+adj.getGender()+" a algo de género "+this.gender+".");
        }
    }

    public abstract String getUnityPronoun();
    public abstract String getSingularPronoun();
    public abstract String getPluralPronoun();
    public abstract String getDistantPronoun();
}
