package com.RandomStories.Leandro.main;

import com.RandomStories.Leandro.view.frames.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}