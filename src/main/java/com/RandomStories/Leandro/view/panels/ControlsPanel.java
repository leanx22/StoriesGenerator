package com.RandomStories.Leandro.view.panels;

import com.RandomStories.Leandro.generator.StoryGenerator;

import javax.swing.*;
import java.awt.*;

public class ControlsPanel extends JPanel {
    JButton generateBtn;
    JButton exportBtn;
    JButton exitBtn;

    public ControlsPanel(){
        initButtons();
        this.add(generateBtn);
        this.add(exportBtn);
        this.add(exitBtn);
    }

    private void initButtons(){
        Dimension buttonSize = new Dimension(150,40);

        this.generateBtn = new JButton("Generar de nuevo");
        this.generateBtn.setPreferredSize(buttonSize);
        this.generateBtn.addActionListener((e)->generate());

        this.exportBtn = new JButton("Exportar historia");
        this.exportBtn.setPreferredSize(buttonSize);

        this.exitBtn = new JButton("Salir");
        this.exitBtn.setPreferredSize(buttonSize);
    }

    private void generate(){
        Thread t = new Thread(StoryGenerator.getInstance());
        if(t.isAlive()){
            System.out.println("Ya se está ejecutando");
            return;
        }
        t.start();
    }

}
