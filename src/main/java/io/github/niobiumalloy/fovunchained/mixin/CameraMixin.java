package io.github.niobiumalloy.fovunchained.mixin;


import io.github.niobiumalloy.fovunchained.config.Config;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "calculateFov", at = @At("RETURN"), cancellable = true)
    private void overrideCalculateFov(float partialTicks, CallbackInfoReturnable<Float> cir) {
        Config config = Config.INSTANCE;
        if (config.customFovEnabled) {
            float vanillaCalculatedFov = cir.getReturnValue();
            double vanillaOptionValue = Minecraft.getInstance().options.fov().get();
            if (vanillaOptionValue > 0) {
                float customCalculatedFov = (float) ((vanillaCalculatedFov / vanillaOptionValue) * config.customFov);
                cir.setReturnValue(customCalculatedFov);
            }
        }
    }
}
