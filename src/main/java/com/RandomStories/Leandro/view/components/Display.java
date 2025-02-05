package com.RandomStories.Leandro.view.components;

import com.RandomStories.Leandro.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Display extends JComponent{
    JTextArea textArea;
    JScrollPane scrollPane;

    public Display(){
        this.setLayout(new BorderLayout());
        initDisplay();
        this.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        print(Constants.DISPLAY_WELCOME_TEXT);
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

    public void print(String text){
        textArea.setText("");
        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(char c: text.toCharArray()){
                    SwingUtilities.invokeLater(()->{textArea.append(String.valueOf(c));});
                    try{
                        Thread.sleep(Constants.DISPLAY_DRAW_TIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        animationThread.start();
    }
}
