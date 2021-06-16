/*
 * This file is part of the AiArt distribution
 * Copyright (c) 2021 AiArt Development
 * See full source at https://github,com/gradleuser/aiart/
 */

package AiArt.Tech.windows.launch;

import javax.swing.*;
import java.awt.*;

public class ProblemWindow extends JFrame {

    ProblemWindow() {
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Problems");
        setLayout(new GridBagLayout());
        JLabel problemLabel = new JLabel();
        problemLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        problemLabel.setText("Hello there, we've identified some problems with your data and worked them out. Here's the scoop: ");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(problemLabel, gbc);
        if(Launch.problems.size() != 0) {
            for(String problem : Launch.problems) {


                switch (problem) {
                    case "missingAiArtFolder" -> {
                        JLabel missingArtFolder = new JLabel();
                        missingArtFolder.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                        missingArtFolder.setText("We found that your entire data folder was missing and replaced it. As a result any saved projects & images are gone.");
                        add(missingArtFolder, gbc);
                    }
                    case "missingSettingFile" -> {
                        JLabel missingSettingFile = new JLabel();
                        missingSettingFile.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                        missingSettingFile.setText("We looked for your settings and came up with nothing, all of your configurations will be gone.");
                        add(missingSettingFile, gbc);
                    }
                    case "missingSetting" -> {
                        JLabel missingSettings = new JLabel();
                        missingSettings.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                        missingSettings.setText("We looked for some of your settings didn't find them, so some of your configurations will be gone.");
                        add(missingSettings, gbc);
                        JLabel quick = new JLabel();
                        quick.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                        quick.setText("Here's the settings you lost: ");
                        add(quick, gbc);
                        JLabel settings = new JLabel();
                        settings.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
                        for (String settingMissing : Launch.missingSettings) {
                            System.out.println(settingMissing);
                            if (settings.getText().equals("")) {
                                settings.setText(settingMissing);
                            } else {
                                settings.setText(settings.getText() + settingMissing);
                            }
                        }
                        add(settings, gbc);
                    }
                }
                JButton okay = new JButton("OK");
                okay.setSize(20, 10);
                add(okay, gbc);
                okay.addActionListener(evt -> {

                    dispose();

                });
                setSize(800,200);
                setLocationRelativeTo(null);
                setUndecorated(false);
                setResizable(false);
                setVisible(true);

            }
            System.out.println("sad trumpet noises");
        } else {
            System.out.println("even sadder trumpet noises");
            dispose();
        }


    }

}
