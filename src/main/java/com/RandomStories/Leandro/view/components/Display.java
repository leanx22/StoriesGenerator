package com.RandomStories.Leandro.view.components;

import javax.swing.*;
import java.awt.*;

public class Display extends JComponent{
    JTextArea textArea;
    JScrollPane scrollPane;

    public Display(){
        this.setLayout(new BorderLayout());
        initDisplay();
        this.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
    }

    private void initDisplay(){
        this.textArea = new JTextArea();
        this.textArea.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        //this.textArea.setEnabled(false);

        this.scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
