package com.RandomStories.Leandro.generator;

import com.RandomStories.Leandro.controller.AdjectivesManager;
import com.RandomStories.Leandro.controller.VerbsManager;
import com.RandomStories.Leandro.model.classes.*;
import com.RandomStories.Leandro.model.classes.Character;
import com.RandomStories.Leandro.model.enumerators.GeneratorStatus;
import com.RandomStories.Leandro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoryGenerator implements Runnable{
    private static StoryGenerator instance;
    private List<StoryStatusListener> listeners;

    private final Random random;
    private final StringBuilder sb;
    private final AdjectivesManager adjectiveManager;
    private final VerbsManager verbManager;


    private String generatedStory;
    private Character[] characters;
    private Place[] places;
    private StoryObject[] objects;
    private Adjective[] adjectives;
    private String[] times;

    private StoryGenerator(){
        listeners = new ArrayList<>();
        random = new Random();
        sb = new StringBuilder();
        adjectiveManager = new AdjectivesManager();
        verbManager = new VerbsManager();
        generatedStory = "";
    }

    public static StoryGenerator getInstance(){
        if(instance==null){
            instance = new StoryGenerator();
        }
        return instance;
    }

    private void loadAssets(){
        callStatusListeners(GeneratorStatus.LOADING_ASSETS);
        try {
            if(characters == null){characters = FileUtils.getCharacters();}
            if(places == null){places = FileUtils.getPlaces();}
            if(objects == null){objects = FileUtils.getObjects();}
            if(adjectives == null){adjectives = FileUtils.getAdjectives();}
            if(times == null){times = FileUtils.getTimes();}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        loadAssets();
        callStatusListeners(GeneratorStatus.CREATING);
        createStory();
        callStatusListeners(GeneratorStatus.DONE);
    }

    public String getLastStory(){
        return generatedStory;
    }

    private void createStory(){
        //restart StringBuilder.
        sb.setLength(0);

        //Select all elements for the story
        String time = times[random.nextInt(times.length)];

        Character mainCharacter = getRandomCharacter();
        Adjective mainCharacterAdjective = adjectiveManager.getRandomAdjective(mainCharacter);

        Place mainPlace = places[random.nextInt(places.length)];

        Verb mainAction = verbManager.getRandomVerb();

        sb.append(time).append(",");
        sb.append(" ").append(mainCharacter.getUnityPronoun()).append(" ").append(mainCharacter);
        sb.append(" ").append(mainCharacterAdjective);
        sb.append(" en ").append(mainPlace.getSingularPronoun()).append(" ").append(mainPlace);
        if(mainCharacter.areMany()){
            sb.append(", ").append(mainAction.getImperfectoPlural());
        }else{
            sb.append(", ").append(mainAction.getImperfecto());
        }

        generatedStory = sb.toString();
    }

    private Character getRandomCharacter(){
        if(characters.length == 0){throw new RuntimeException("Cant get a character because the list is empty.");}
        return characters[random.nextInt(characters.length)];
    }

    public void addStatusListener(StoryStatusListener l){
        this.listeners.add(l);
    }

    public void clearStatusListeners(){
        this.listeners.clear();
    }

    private void callStatusListeners(GeneratorStatus s){
        this.listeners.forEach(e->e.onStatusChanged(s));
    }

}
