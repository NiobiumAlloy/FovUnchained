package io.github.niobiumalloy.fovunchained.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.niobiumalloy.fovunchained.FovUnchained;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("FovUnchained.json");

    public static void load() {
        File file = CONFIG_PATH.toFile();
        if (!file.exists()) {

            FovUnchained.LOGGER.info("FovUnchained config created");
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            Config.INSTANCE = GSON.fromJson(reader, Config.class);
            FovUnchained.LOGGER.info("FovUnchained config loaded.");
        } catch (IOException e) {
            FovUnchained.LOGGER.error("Failed to load FovUnchained config", e);
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(Config.INSTANCE, writer);
        } catch (IOException e) {
            FovUnchained.LOGGER.error("Failed to save FovUnchained config", e);
        }
    }
}