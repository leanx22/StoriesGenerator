package com.RandomStories.Leandro.view.panels;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton generateBtn;
    JButton exportBtn;
    JButton exitBtn;

    public ButtonPanel(){
        initButtons();
        this.add(generateBtn);
        this.add(exportBtn);
        this.add(exitBtn);
    }

    private void initButtons(){
        Dimension buttonSize = new Dimension(150,40);

        this.generateBtn = new JButton("Generar de nuevo");
        this.generateBtn.setPreferredSize(buttonSize);

        this.exportBtn = new JButton("Exportar historia");
        this.exportBtn.setPreferredSize(buttonSize);

        this.exitBtn = new JButton("Salir");
        this.exitBtn.setPreferredSize(buttonSize);
    }

}
