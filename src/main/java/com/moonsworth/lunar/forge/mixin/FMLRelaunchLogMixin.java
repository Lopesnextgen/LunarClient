package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FMLRelaunchLog.class)
public class FMLRelaunchLogMixin {
   @Shadow
   public static Side side;

   public FMLRelaunchLogMixin() {
   }

   @Inject(method = "configureLogging", at = @At("HEAD"))
   private static void ichor$configureLogging(CallbackInfo callback0) {
      side = Side.CLIENT;
   }
}
