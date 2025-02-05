package com.RandomStories.Leandro.generator;

import com.RandomStories.Leandro.controller.AdjectivesManager;
import com.RandomStories.Leandro.controller.VerbsManager;
import com.RandomStories.Leandro.model.classes.*;
import com.RandomStories.Leandro.model.classes.Character;
import com.RandomStories.Leandro.model.enumerators.GeneratorStatus;
import com.RandomStories.Leandro.utils.FileUtils;

import java.io.IOException;
import java.util.*;

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
    private String[] transitions;

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
            if(transitions == null){transitions = FileUtils.getTransitions();}
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

        Map<String, Object> elements = getNewStoryElements();

        sb.append((String)elements.get("time")).append(", ");
        Character mainCharacter = (Character)elements.get("mainCharacter");
        sb.append(mainCharacter.getUnityPronoun()).append(" ").append(mainCharacter).append(" ").append(mainCharacter.getAdjectiveString());
        Place mainPlace = (Place)elements.get("mainPlace");
        sb.append(" en ").append(mainPlace.getUnityPronoun()).append(" ").append(mainPlace).append(", ");
        Verb mainAction = (Verb)elements.get("mainAction");
        sb.append(mainCharacter.areMany() ? mainAction.getImperfectoPlural() : mainAction.getImperfecto()).append(" ");
        StoryObject mainObject = (StoryObject)elements.get("mainObject");
        sb.append(mainObject.getUnityPronoun()).append(" ").append(mainObject).append(". ");
        sb.append((String)elements.get("transition")).append(" ");
        sb.append(mainObject.getDistantPronoun()).append(" ").append(mainObject).append(" ");
        sb.append(mainObject.areMany() ? "eran" : "era").append(" ");
        sb.append(mainObject.getAdjectiveString()).append(" ");
        sb.append("y por ende, ");//TODO
        Verb twistAction = (Verb)elements.get("twistAction");
        sb.append(mainObject.areMany() ? twistAction.getSimplePlural():twistAction.getSimple());
        //TODO -> un/una unos/unas mesas/sillas/autitos...
        sb.append(".");
        generatedStory = sb.toString();
    }

    ///Prepare and returns all the elements that will be used in the story.
    private Map<String, Object> getNewStoryElements(){
        Map<String, Object> elements = new HashMap<>();

        String storyTime = times[random.nextInt(times.length)];
        elements.put("time", storyTime);

        Character mainCharacter = getRandomCharacter();
        mainCharacter.setAdjective(adjectiveManager.getRandomAdjective(mainCharacter));
        elements.put("mainCharacter", mainCharacter);

        Place mainPlace = places[random.nextInt(places.length)];
        elements.put("mainPlace", mainPlace);

        Verb mainAction = verbManager.getRandomVerb();
        elements.put("mainAction", mainAction);

        StoryObject mainObject = objects[random.nextInt(objects.length)];
        mainObject.setAdjective(adjectiveManager.getRandomAdjective(mainObject));
        elements.put("mainObject", mainObject);

        String transition = transitions[random.nextInt(transitions.length)];
        elements.put("transition", transition);

        Verb twistAction = verbManager.getRandomVerb();
        elements.put("twistAction", twistAction);

        return elements;
    }

    private Character getRandomCharacter(){
        if(characters.length == 0){throw new RuntimeException("Cant get a character because the list is empty.");}
        return characters[random.nextInt(characters.length)];
    }

    private StoryObject getRandomObject(){
        if(objects.length == 0){throw new RuntimeException("Cant get an object because the list is empty.");}
        return objects[random.nextInt(objects.length)];
    }

    private String getRandomTransition(){
        if(transitions.length == 0){throw new RuntimeException("Cant get a transition because the list is empty.");}
        return transitions[random.nextInt(transitions.length)];
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
