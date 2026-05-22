package com.failed.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static final Logger LOGGER = LoggerFactory.getLogger("failedshurtcam");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "failedshurtcam.json");

    // Default configuration
    public static boolean disableHurtCam = true;

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                if (data != null) {
                    disableHurtCam = data.disableHurtCam;
                }
            } catch (IOException e) {
                LOGGER.error("Failed to load config", e);
            }
        } else {
            save(); // Create the default config file
        }
    }

    public static void save() {
        try {
            if (!CONFIG_FILE.getParentFile().exists()) {
                CONFIG_FILE.getParentFile().mkdirs();
            }
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(new ConfigData(disableHurtCam), writer);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to save config", e);
        }
    }

    private static class ConfigData {
        public boolean disableHurtCam;

        public ConfigData(boolean disableHurtCam) {
            this.disableHurtCam = disableHurtCam;
        }
    }
}
