package com.RandomStories.Leandro.generator;

import com.RandomStories.Leandro.model.classes.Adjective;
import com.RandomStories.Leandro.model.classes.Place;
import com.RandomStories.Leandro.model.classes.StoryObject;
import com.RandomStories.Leandro.model.enumerators.GeneratorStatus;
import com.RandomStories.Leandro.utils.FileUtils;
import com.RandomStories.Leandro.model.classes.Character;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoryGenerator implements Runnable{
    private static StoryGenerator instance;
    private List<StoryStatusListener> listeners;

    private final Random random;
    private final StringBuilder sb;

    private String generatedStory;
    private Character[] characters;
    private Place[] places;
    private StoryObject[] objects;
    private Adjective[] adjectives;

    private StoryGenerator(){
        listeners = new ArrayList<>();
        random = new Random();
        sb = new StringBuilder();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("toy corriendo");
        loadAssets();
        callStatusListeners(GeneratorStatus.CREATING);
        System.out.println("toy creando");
        createStory();
        callStatusListeners(GeneratorStatus.DONE);
    }

    public String getLastStory(){
        return generatedStory;
    }

    private void createStory(){
        sb.setLength(0); //restarts the string builder.

        String time = "Había una vez, ";
        sb.append(time);

        Character mainCharacter = getRandomCharacter();
        sb.append(" "+mainCharacter.getUnityPronoun()+" "+mainCharacter);

        Adjective mainCharacterAdjective = adjectives[random.nextInt(adjectives.length)];
        sb.append(" "+mainCharacterAdjective);

        Place mainPlace = places[random.nextInt(places.length)];
        sb.append(" en "+mainPlace.getSingularPronoun()+" "+mainPlace);

        generatedStory = sb.toString();
        System.out.println("generación: "+generatedStory);
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
        System.out.println("LLAMANDO LISTENERS - ESTADO: "+s);
        this.listeners.forEach(e->e.onStatusChanged(s));
    }

}
