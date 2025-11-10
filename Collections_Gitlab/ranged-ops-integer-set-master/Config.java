package com.epam.rd.autocode.map;

import java.io.*;
import java.util.Properties;

public class Config {

    private Properties config;

    public Config() {
        try {
            reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() throws IOException {
        // Step 1: Load config.properties
        Properties temp = new Properties();
        temp.load(new FileReader("config.properties"));

        // Step 2: Read default.filenames if present
        String defaults = temp.getProperty("default.filenames");

        Properties defaultsChain = null;

        if (defaults != null && !defaults.trim().isEmpty()) {
            String[] files = defaults.split(",");

            // Load in reverse order
            for (int i = files.length - 1; i >= 0; i--) {
                String fileName = files[i].trim() + ".properties";
                Properties p = new Properties(defaultsChain);
                try (FileReader reader = new FileReader(fileName)) {
                    p.load(reader);
                }
                defaultsChain = p;
            }
        }

        // Step 3: Create main config with chained defaults
        config = new Properties(defaultsChain);
        try (FileReader reader = new FileReader("config.properties")) {
            config.load(reader);
        }
    }

    public String get(String key) {
        return config.getProperty(key);
    }

    public void set(String key, String value) {
        config.setProperty(key, value);
    }

    public void remove(String key) {
        config.remove(key);
    }

    public void save() {
        try (FileWriter writer = new FileWriter("config.properties")) {
            // Save only main properties (top-level)
            config.store(writer, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
