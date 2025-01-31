package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;

public class StoryObject extends Thing{
    protected boolean areMany;

    public StoryObject(String objectName, Gender objectGender, boolean areMany,String objectAdjective){
        super(objectName, objectGender, objectAdjective);
        this.areMany = areMany;
    }

    public StoryObject(String objectName, Gender objectGender, boolean areMany){
        super(objectName, objectGender);
        this.areMany = areMany;
    }

    public boolean areMany(){
        return areMany;
    }


    @Override
    public String getUnityPronoun() {
        if(areMany){return super.gender == Gender.MALE ? "unos" : "unas";}
        return super.gender == Gender.MALE ? "un" : "una";
    }

    @Override
    public String getSingularPronoun() {
        if(areMany){return getPluralPronoun();}
        return super.gender == Gender.MALE ? "el" : "la";
    }

    @Override
    public String getPluralPronoun() {
        if(!areMany){return getSingularPronoun();}
        return super.gender == Gender.MALE ? "los" : "las";
    }

    @Override
    public String getDistantPronoun() {
        if(!areMany){
            return super.gender == Gender.MALE ? "aquel" : "aquella";
        }
        return super.gender == Gender.MALE ? "aquellos" : "aquellas";
    }
}
