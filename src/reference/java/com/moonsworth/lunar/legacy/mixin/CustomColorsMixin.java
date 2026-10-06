package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.world.World;
import net.optifine.CustomColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(CustomColors.class)
public abstract class CustomColorsMixin {
   public CustomColorsMixin() {
   }

   @Inject(method = "updateLightmap", at = @At("HEAD"), cancellable = true)
   private static void impl$updateLightMap(World world0, float value, int[] items2, boolean flag, float value2, CallbackInfoReturnable<Boolean> callbackinforeturnable5) {
      if (Ref.method4().method40().method56().method13()) {
         callbackinforeturnable5.setReturnValue(false);
      }
   }
}
