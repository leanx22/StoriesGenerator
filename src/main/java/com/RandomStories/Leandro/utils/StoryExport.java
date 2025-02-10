package com.RandomStories.Leandro.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StoryExport {
    public static void saveStringToFile(String content) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar historia");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filter to only txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false); //Removes "All files" type options.

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("SFile: "+selectedFile);
            // Check if file has .txt on its name, if not, then concatenates it.
            if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }

            // Save to file.
            try (FileWriter writer = new FileWriter(selectedFile)) {
                writer.write(content);
                JOptionPane.showMessageDialog(null, "Archivo guardado con éxito en:\n" + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Hubo un error al escribir el archivo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se exportó la historia.");
        }
    }
}