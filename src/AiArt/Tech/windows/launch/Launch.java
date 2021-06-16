/*
 This file is part of the AiArt distribution
 Copyright © 2021 AiArt Development
 See full source at https://github.com/gradleuser/aiart/
 */

package AiArt.Tech.windows.launch;


import AiArt.Tech.resources.Settings.Settings;
import AiArt.Tech.resources.utils.SystemUtils;
import AiArt.Tech.windows.MainWindow;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;


public class Launch extends JFrame{
    JLabel currentValue = new JLabel("Surely Doing Something...");
    public static ArrayList<String> problems = new ArrayList<>();
    public static ArrayList<String> missingSettings = new ArrayList<>();



    public Launch() {
        currentValue.setForeground(Color.black);
        currentValue.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        try {
            BufferedImage icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/icon.png")));
            ImageIcon imgIcon = new ImageIcon(icon);
            setIconImage(imgIcon.getImage());


        } catch(Exception e) {
            System.out.println("uh, " + e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setTitle("AiArt - Launch");
        setUndecorated(true);

        setContentPane(new JPanel() {
            final URL url = getClass().getResource("resources/launchGif.gif");
            final ImageIcon gif = new ImageIcon(url);
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gif.getImage(), 0, 0, 480, 480, this);
            }
        });


        setLayout(new GridBagLayout());
        getContentPane().add(currentValue, new GridBagConstraints());
        setSize(470,460);
        setLocationRelativeTo(null);
        setVisible(true);
        currentValue.setText("Checking & Fixing The Data Folder");
        getContentPane().remove(currentValue);
        getContentPane().add(currentValue);


        if (getAiArtFolder()) {
            getSettingFile();

        }
        new MainWindow();
        new ProblemWindow();
        dispose();



    }

    private boolean getAiArtFolder()  {
        File jsonFile = new File(SystemUtils.ArtAiFolder());
        boolean fileExists = jsonFile.exists();


        if(!fileExists){
            try {
                String sourcePath = "src/AiArt/Tech/resources/AiArt";
                File sourceFile = new File(sourcePath);
                String sourceAbsolutePath = sourceFile.getAbsolutePath();

                String destPath = SystemUtils.ArtAiFolder();
                File destFile = new File(destPath);
                String destAbsolutePath = destFile.getAbsolutePath();

                SystemUtils.copyDirectory(sourceAbsolutePath, destAbsolutePath);

                problems.add("missingAiArtFolder");


            } catch(Exception e) {
                System.out.println("oh noes something happened " + e);
            }
        }

        return fileExists;

    }

    private static void getSettingFile() {

            File supposedLocation = new File(SystemUtils.ArtAiFolder() + "/settings.json");

            if(!supposedLocation.exists()){
                try {
                    SystemUtils.copyFileUsingStream(new File(new File("src/AiArt/Tech/resources/AiArt/settings.json").getAbsolutePath()), supposedLocation);

                    problems.add("missingSettingFile");
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            } else {
                //{{{name, type, saveName}, {type1, type2}, {type2}}, ect.}
                String[][][] settings = {Settings.lightMode()};


                for(String[][] setting : settings) {
                    try {

                        // create a reader
                        Reader reader = Files.newBufferedReader(Paths.get(SystemUtils.ArtAiFolder() + "/settings.json"));

                        // create parser
                        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);

                        // read customer details
                        String settingChoice = (String) parser.get(setting[0][2]);


                        if(settingChoice == null) {
                            // create a writer
                            String settingsString = FileUtils.readFileToString(new File(SystemUtils.ArtAiFolder() + "/settings.json"));
                            StringBuilder withSetting = new StringBuilder(settingsString);
                            withSetting.deleteCharAt(withSetting.length()-1);
                            String comma = ",";
                            if(withSetting.length() < 3){
                                comma = "";
                            }
                            String editedSettingsString = withSetting + comma + "\"" + setting[0][2] + "\":\"" + setting[1][0] + "\"}";
                            FileUtils.writeStringToFile(new File(SystemUtils.ArtAiFolder() + "/settings.json"), editedSettingsString);

                            missingSettings.add(setting[0][0]);
                            if(!problems.contains("missingSetting")) {
                                problems.add("missingSetting");
                            }
                        }

                        //close reader
                        reader.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }


    }



}
