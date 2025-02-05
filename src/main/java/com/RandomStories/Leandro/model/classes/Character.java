package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;

public class Character extends StoryObject {
    public Character(String characterName, Gender characterGender, boolean areMany, Adjective adjective){
        super(characterName, characterGender, areMany, adjective);
    }

    public Character(String characterName, Gender characterGender, boolean areMany){
        super(characterName, characterGender, areMany);
    }
}
