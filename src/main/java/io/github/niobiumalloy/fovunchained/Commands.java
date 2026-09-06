package io.github.niobiumalloy.fovunchained;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import io.github.niobiumalloy.fovunchained.config.Config;
import io.github.niobiumalloy.fovunchained.config.ConfigManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

public class Commands {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        Config config = Config.INSTANCE;

        dispatcher.register(ClientCommands.literal("fovunchained")
                .then(ClientCommands.literal("help")
                        .executes(context -> {
                            FabricClientCommandSource src = context.getSource();
                            src.sendFeedback(Component.literal("§6--- FovUnchained command help ---"));
                            src.sendFeedback(Component.literal("§e/fovunchained setFov <int>"));
                            src.sendFeedback(Component.literal("§e/fovunchained getFov"));
                            src.sendFeedback(Component.literal("§e/fovunchained toggleCustomFov"));
                            src.sendFeedback(Component.literal("§e/fovunchained setSwingSpeed <float>"));
                            src.sendFeedback(Component.literal("§e/fovunchained getSwingSpeed"));
                            src.sendFeedback(Component.literal("§e/fovunchained toggleCustomSwingSpeed"));
                            return 1;
                        })
                )
                .then(ClientCommands.literal("setFov")
                        .then(ClientCommands.argument("fov", IntegerArgumentType.integer())
                                .executes(context -> {
                                    config.customFov = IntegerArgumentType.getInteger(context, "fov");
                                    ConfigManager.save();
                                    FabricClientCommandSource src = context.getSource();
                                    src.sendFeedback(Component.literal("§6CustomFov set to: " + config.customFov));
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
                .then(ClientCommands.literal("setSwingSpeed")
                        .then(ClientCommands.argument("speed", FloatArgumentType.floatArg(0.1f))
                                .executes(context -> {
                                    config.swingSpeedMultiplier = FloatArgumentType.getFloat(context, "speed");
                                    ConfigManager.save();
                                    FabricClientCommandSource src = context.getSource();
                                    src.sendFeedback(Component.literal("§6SwingSpeedMultiplier set to: " + config.swingSpeedMultiplier));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommands.literal("getSwingSpeed")
                        .executes(context -> {
                            FabricClientCommandSource src = context.getSource();
                            src.sendFeedback(Component.literal("§6SwingSpeedMultiplier: " + config.swingSpeedMultiplier));
                            return 1;
                        })
                )
                .then(ClientCommands.literal("toggleCustomSwingSpeed")
                        .executes(context -> {
                            config.customSwingSpeedEnabled = !config.customSwingSpeedEnabled;
                            ConfigManager.save();
                            FabricClientCommandSource src = context.getSource();
                            src.sendFeedback(Component.literal("§6CustomSwingSpeedEnabled set to: " + config.customSwingSpeedEnabled));
                            return 1;
                        })
                )
        );
    }
}