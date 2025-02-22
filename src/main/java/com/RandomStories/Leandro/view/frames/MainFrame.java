package com.RandomStories.Leandro.view.frames;

import com.RandomStories.Leandro.utils.Constants;
import com.RandomStories.Leandro.view.panels.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setTitle(Constants.APP_TITLE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(640,480);

        this.add(new MainPanel());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
