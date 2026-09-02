package io.github.niobiumalloy.fovunchained;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import io.github.niobiumalloy.fovunchained.config.Config;
import io.github.niobiumalloy.fovunchained.config.ConfigManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

public class Commands {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        Config config =  Config.INSTANCE;

        dispatcher.register(ClientCommands.literal("fovunchained")
                .then(ClientCommands.literal("help")
                        .executes(context -> {
                            FabricClientCommandSource src = context.getSource();
                            src.sendFeedback(Component.literal("§6--- FovUnchained command help ---"));

                            return 1;
                        })
                )
                .then(ClientCommands.literal("setFov")
                        .then(ClientCommands.argument("fov", IntegerArgumentType.integer())
                                .executes(context -> {
                                    config.customFov = IntegerArgumentType.getInteger(context, "fov");
                                    ConfigManager.save();

                                    return 1;
                                })
                        )
                )
                .then(ClientCommands.literal("getFov")
                        .executes(context -> {
                            FabricClientCommandSource src = context.getSource();
                            src.sendFeedback(Component.literal("§6CustomFov: " + config.customFov));
                            return 1;
                        })
                )
                .then(ClientCommands.literal("toggleCustomFov")
                        .executes(context -> {
                            config.customFovEnabled = !config.customFovEnabled;
                            ConfigManager.save();
                            FabricClientCommandSource src = context.getSource();
                            src.sendFeedback(Component.literal("§6CustomFovEnabled set to: " + config.customFovEnabled));
                            return 1;
                        })
                )
        );
    }
}