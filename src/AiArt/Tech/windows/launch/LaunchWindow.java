/*
 This file is part of the AiArt distribution
 Copyright © 2021 AiArt Development
 See full source at https://github.com/gradleuser/aiart/
 */

package AiArt.Tech.windows.launch;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

public class LaunchWindow extends JFrame{
    JButton launchButton = new javax.swing.JButton();
    JButton closeButton = new javax.swing.JButton();
    JPanel space =new JPanel();


    public LaunchWindow()  {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setTitle("AiArt - Launch");

        try {
            BufferedImage icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/icon.png")));
            ImageIcon imgIcon = new ImageIcon(icon);
            setIconImage(imgIcon.getImage());


        } catch(Exception e) {
            System.out.println("uh, " + e);
        }

        //Background gif
        try {
            setContentPane(new JPanel() {
                final URL url = getClass().getResource("resources/launchGif.gif");
                final ImageIcon gif = new ImageIcon(url);
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(gif.getImage(), 0, -15, 480, 480, this);
                }
            });
        } catch (Exception e) {
            System.out.println("uh, " + e);
        }


        launchButton();
        //For some reason this doesn't work if I put it in launchButton()
        launchButton.addActionListener(evt -> {

            dispose();
            new Launch();

        });

        space.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        setFocusable(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(launchButton, gbc);
        closeButton();

        closeButton.addActionListener(evt -> System.exit(0));
        space.setBorder(new EmptyBorder(10, 10, 10, 10));
        space.setOpaque(false);
        add(launchButton, gbc);
        add(space, gbc);
        add(closeButton, gbc);
        setSize(480,480);
        setLocationRelativeTo(null);
        setUndecorated(false);



        setVisible(true);


    }


    private void launchButton() {

        //The launch button you see in the middle of the launch window

        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/launch1.png")));
            BufferedImage img2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/launch2.png")));
            launchButton.setIcon(new ImageIcon(img));
            launchButton.setRolloverIcon(new ImageIcon(img2));
            launchButton.setPressedIcon(new ImageIcon(img2));
            launchButton.setContentAreaFilled(false);
            launchButton.setBorderPainted(false);
            launchButton.setBorder(BorderFactory.createEmptyBorder());
            launchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            launchButton.setFocusable(false);
        } catch(Exception e) {
            System.out.println("uh, " + e);
        }


    }

    private void closeButton() {
        //The launch button you see below the middle of the launch window
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/close1.png")));
            BufferedImage img2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/close2.png")));
            closeButton.setIcon(new ImageIcon(img));
            closeButton.setRolloverIcon(new ImageIcon(img2));
            closeButton.setPressedIcon(new ImageIcon(img2));
            closeButton.setContentAreaFilled(false);
            closeButton.setBorderPainted(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder());
            closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (Exception e) {
            System.out.print("uh, " + e);
        }
    }





}