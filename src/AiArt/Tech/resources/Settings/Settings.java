/*
 This file is part of the AiArt distribution
 Copyright © 2021 AiArt Development
 See full source at https://github.com/gradleuser/aiart/
 */

package AiArt.Tech.resources.Settings;

import AiArt.Tech.resources.utils.SystemUtils;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Settings {

    public static String[][] lightMode()  {
        String[] values = {"Dark", "Light"};
        String name = "Light Mode";
        String type = "fullApp";
        String saveName = "lightMode";
        return new String[][]{new String[]{name, type, saveName}, values};
    }

    public static String getCurrentChoice(String settingName) {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(SystemUtils.ArtAiFolder() + "/settings.json"));

            // create parser
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);

            String settingChoice = (String) parser.get(settingName);


            //close reader
            reader.close();

            return settingChoice;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "problem";
    }



}
