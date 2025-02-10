package com.RandomStories.Leandro.view.components;

import com.RandomStories.Leandro.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Display extends JComponent{
    JTextArea textArea;
    JScrollPane scrollPane;
    private Thread animationThread;
    private volatile boolean animationInterruptionFlag = false;

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
        this.textArea.setEnabled(false);

        this.textArea.setDisabledTextColor(Color.BLACK);
        textArea.setFont(textArea.getFont().deriveFont(18f));

        this.scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }


    public void print(String text) {
        // Si hay un hilo en ejecución
        if (animationThread != null && animationThread.isAlive()) {
            animationInterruptionFlag = false; // Pido al hilo que se detenga
            try {
                animationThread.join(); // Espero a que termine
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        textArea.setText("");
        animationInterruptionFlag = true;

        animationThread = new Thread(() -> {
            for (char c : text.toCharArray()) {
                if (!animationInterruptionFlag) { //Verifico si se pidió la interrupción
                    return;
                }

                SwingUtilities.invokeLater(() -> textArea.append(String.valueOf(c)));

                try {
                    Thread.sleep(Constants.DISPLAY_DRAW_TIME);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        animationThread.start();
    }

    public String getDisplayContent(){
        return this.textArea.getText();
    }

}
