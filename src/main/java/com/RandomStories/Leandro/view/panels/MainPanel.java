package com.RandomStories.Leandro.view.panels;

import com.RandomStories.Leandro.view.components.Display;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    Display display = new Display();

    public MainPanel(){
        this.setLayout(new BorderLayout());
        this.add(new ButtonPanel(), BorderLayout.SOUTH);

        this.add(display, BorderLayout.CENTER);
    }


}
