package io.github.niobiumalloy.fovunchained.mixin;


import io.github.niobiumalloy.fovunchained.config.Config;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "getCurrentSwingDuration", at = @At("RETURN"), cancellable = true)
    private void modifySwingDuration(CallbackInfoReturnable<Integer> cir) {
        if ((Object) this instanceof LocalPlayer) {
            boolean isEnabled = Config.INSTANCE.customSwingSpeedEnabled;
            float multiplier = Config.INSTANCE.swingSpeedMultiplier;
            if (isEnabled) {
                int originalDuration = cir.getReturnValue();
                int newDuration = Math.max(1, Math.round(originalDuration / multiplier));
                cir.setReturnValue(newDuration);
            }
        }
    }
}