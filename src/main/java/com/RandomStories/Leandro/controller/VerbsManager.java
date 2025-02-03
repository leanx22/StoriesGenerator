package com.RandomStories.Leandro.controller;

import com.RandomStories.Leandro.model.classes.Verb;
import com.RandomStories.Leandro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VerbsManager {
    List<Verb> allVerbs = new ArrayList<>();

    private final Random random;

    public VerbsManager(){
        this.random = new Random();
        loadVerbs();
    }

    private void loadVerbs(){
        try {
            allVerbs = List.of(FileUtils.getVerbs());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    ///Return a random verb from the list.
    public Verb getRandomVerb(){
        return allVerbs.get(random.nextInt(allVerbs.size()));
    }
}
