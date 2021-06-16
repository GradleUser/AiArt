/*
 This file is part of the AiArt distribution
 Copyright © 2021 AiArt Development
 See full source at https://github.com/gradleuser/aiart/
 */

package AiArt.Tech.windows;

import AiArt.Tech.resources.Settings.Settings;
import AiArt.Tech.resources.utils.SystemUtils;

import javax.swing.*;
import java.awt.*;
public class MainWindow extends JFrame {

 JLabel label = new JLabel("Hello!");
 
 public MainWindow(){
  
  label.setBounds(0,0,100,50);
  label.setFont(new Font(null,Font.PLAIN,25));

try {
 if (Settings.getCurrentChoice(Settings.lightMode()[0][2]).equals("Dark")) {

  label.setForeground(Color.white);
  getContentPane().setBackground(Color.darkGray);

 } else if (Settings.getCurrentChoice(Settings.lightMode()[0][2]).equals("Light")) {

  label.setForeground(Color.darkGray);
  getContentPane().setBackground(Color.lightGray);


 }
} catch(Exception e){
 //do smth here
}
  
  add(label);

  setDefaultCloseOperation(EXIT_ON_CLOSE);
  ImageIcon imgIcon = new ImageIcon(SystemUtils.ArtAiFolder() + "\\resources\\icon.png");
  setIconImage(imgIcon.getImage());
  setTitle("AiArt - Making Art Through Artificial Intelligence");
  setSize(420,420);
  setLayout(null);
  setLocationRelativeTo(null);
  setVisible(true);


 }




}