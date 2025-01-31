package com.RandomStories.Leandro.generator;

import com.RandomStories.Leandro.model.enumerators.GeneratorStatus;

import java.util.EventListener;

public interface StoryStatusListener extends EventListener {
    public void onStatusChanged(GeneratorStatus status);
}
