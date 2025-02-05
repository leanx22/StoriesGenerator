package com.RandomStories.Leandro.controller;

import com.RandomStories.Leandro.model.classes.Adjective;
import com.RandomStories.Leandro.model.classes.StoryObject;
import com.RandomStories.Leandro.model.enumerators.Gender;
import com.RandomStories.Leandro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdjectivesManager {
    List<Adjective> maleAdjectives = new ArrayList<>();
    List<Adjective> femaleAdjectives = new ArrayList<>();
    List<Adjective> neutralAdjectives = new ArrayList<>();
    List<Adjective> allAdjectives = new ArrayList<>();


    private final Random random;

    public AdjectivesManager(){
        random = new Random();
        loadAdjectives();
    }

    private void loadAdjectives(){
        try {
            allAdjectives = List.of(FileUtils.getAdjectives()); //hacer que directamente retorne una lista si no lo voy a usar
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        for(Adjective adj: allAdjectives){
            sortAndAdd(adj);
        }
    }

    private void sortAndAdd(Adjective adj){
        if(adj.getGender() == Gender.MALE){
            maleAdjectives.add(adj);
        } else if (adj.getGender() == Gender.FEMALE) {
            femaleAdjectives.add(adj);
        }else{
            neutralAdjectives.add(adj);
        }
    }

    public Adjective getRandomAdjective(){
        return allAdjectives.get(random.nextInt(allAdjectives.size()));
    }

    public Adjective getRandomAdjective(StoryObject object){
        Adjective result;
        if(object.getGender() == Gender.MALE){
            result = maleAdjectives.get(random.nextInt(maleAdjectives.size()));
        } else if (object.getGender() == Gender.FEMALE) {
            result = femaleAdjectives.get(random.nextInt(femaleAdjectives.size()));
        }else{
            result = neutralAdjectives.get(random.nextInt(neutralAdjectives.size()));
        }

        return result;
    }
}
