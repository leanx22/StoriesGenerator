package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;

public class Place extends StoryObject{

    public Place(String placeName, Gender gender, boolean isPlural, Adjective adjective){
        super(placeName, gender, isPlural, adjective);
    }

    public Place(String placeName, Gender gender, boolean isPlural){
        super(placeName, gender, isPlural);
    }

}
