package com.RandomStories.Leandro.view.panels;

import com.RandomStories.Leandro.generator.StoryGenerator;
import com.RandomStories.Leandro.utils.StoryExport;
import com.RandomStories.Leandro.view.components.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ControlsPanel extends JPanel {
    JButton generateBtn;
    JButton exportBtn;
    JButton exitBtn;
    JButton copyBtn;
    Display display;

    public ControlsPanel(Display mainDisplay){
        initButtons();
        this.display = mainDisplay;
        this.add(generateBtn);
        this.add(exportBtn);
        this.add(copyBtn);
        this.add(exitBtn);
    }

    private void initButtons(){
        Dimension buttonSize = new Dimension(150,40);

        this.generateBtn = new JButton("Generar de nuevo");
        this.generateBtn.setPreferredSize(buttonSize);
        this.generateBtn.addActionListener((e)->generate());

        this.copyBtn = new JButton("Al portapapeles");
        this.copyBtn.setPreferredSize(buttonSize);
        this.copyBtn.addActionListener((e)->toClipboard());

        this.exportBtn = new JButton("Exportar historia");
        this.exportBtn.setPreferredSize(buttonSize);
        this.exportBtn.addActionListener((e)->export());

        this.exitBtn = new JButton("Salir");
        this.exitBtn.setPreferredSize(buttonSize);
        this.exitBtn.addActionListener((e)->{
            System.exit(0);
        });
    }

    private void generate(){
        Thread t = new Thread(StoryGenerator.getInstance());
        if(t.isAlive()){
            System.out.println("Ya se está ejecutando");
            return;
        }
        t.start();
    }

    private void toClipboard(){
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(new StringSelection(display.getDisplayContent()), null);
    }

    private void export(){
        StoryExport.saveStringToFile(display.getDisplayContent());
    }
}
