/*
 This file is part of the AiArt distribution
 Copyright © 2021 AiArt Development
 See full source at https://github.com/gradleuser/aiart/
 */

package AiArt.Tech.resources.utils;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SystemUtils {

    //Returns the location of the ArtAi Folder
    public static String ArtAiFolder() {
        //Get Os
        String os = System.getProperty("os.name").toLowerCase();


            //Windows
            if (os.contains("win")) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    return System.getenv("AppData") + "/AiArt";
                }
                //MacOs
            } else if (os.contains("mac")) {
                String path = System.getProperty("user.home") + "/Library/Application Support/AiArt";
                return path;

                //NixOs - Linux
            } else if (os.contains("nix") || os.contains("nux")) {
                String path = System.getProperty("user.home") + "/AiArt";
                return path;

            }

            return "problem";
    }

    public static void openUrl(String url) {

        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(url));
                }
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


    //Courtesy of Baeldung
    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            assert is != null;
            is.close();
            assert os != null;
            os.close();
        }
    }

    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
            throws IOException {
        Files.walk(Paths.get(sourceDirectoryLocation))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                            .substring(sourceDirectoryLocation.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static String insertStringIntoString(String originalString, int position, String toBeInserted) {
        int startIndex = 0;
        int endIndex = originalString.length();
        StringBuilder newString = new StringBuilder();

        for (int i = startIndex; i < endIndex; i++) {
            // Insert the original string character into the new string
            newString.append(originalString.charAt(startIndex));

            if (startIndex == position) {
                // Insert the string to be inserted into the new string
                newString.append(toBeInserted);
            }
        }

        return newString.toString();
    }







}





