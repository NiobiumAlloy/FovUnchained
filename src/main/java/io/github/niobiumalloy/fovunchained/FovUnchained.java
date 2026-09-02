package io.github.niobiumalloy.fovunchained;

import io.github.niobiumalloy.fovunchained.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FovUnchained implements ClientModInitializer {
    public static final String MOD_ID = "fovunchained";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        ConfigManager.load();

        ClientCommandRegistrationCallback.EVENT.register(Commands::register);
    }
}
