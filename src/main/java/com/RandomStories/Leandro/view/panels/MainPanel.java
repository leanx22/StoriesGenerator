package com.RandomStories.Leandro.view.panels;

import com.RandomStories.Leandro.generator.StoryGenerator;
import com.RandomStories.Leandro.model.enumerators.GeneratorStatus;
import com.RandomStories.Leandro.view.components.Display;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    Display display;

    public MainPanel(){
        this.setLayout(new BorderLayout());
        init();
        this.add(new ControlsPanel(display), BorderLayout.SOUTH);
    }

    private void init(){
        display = new Display();
        this.add(display, BorderLayout.CENTER);
        StoryGenerator.getInstance().addStatusListener(this::onStoryGenerationComplete);
    }

    private void onStoryGenerationComplete(GeneratorStatus status){
        switch (status){
            case DONE -> display.print(StoryGenerator.getInstance().getLastStory());
        }
    }

}
