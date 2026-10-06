package com.moonsworth.lunar.v1_7.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.world.World;
import net.optifine.v1_7.CustomColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CustomColorizer.class)
public abstract class CustomColorizerMixin {
   @Inject(method = "updateLightmap", at = @At("HEAD"), cancellable = true)
   private static void impl$updateLightMap(World var0, float var1, int[] var2, boolean var3, CallbackInfoReturnable<Boolean> var4) {
      if (ThreadModuleDump63.method4().method40().method56().method13()) {
         var4.setReturnValue(false);
      }
   }
}
