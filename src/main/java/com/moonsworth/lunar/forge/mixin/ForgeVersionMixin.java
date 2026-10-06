package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.common.ForgeVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ForgeVersion.class)
public abstract class ForgeVersionMixin {
   public ForgeVersionMixin() {
   }

   @Shadow
   public static int getMajorVersion() {
      return 0;
   }

   @Shadow
   public static int getMinorVersion() {
      return 0;
   }

   @Shadow
   public static int getRevisionVersion() {
      return 0;
   }

   @Shadow
   public static int getBuildVersion() {
      return 0;
   }

   @Inject(method = "getVersion", at = @At("HEAD"), cancellable = true)
   private static void ichor$forgeVersion(CallbackInfoReturnable<String> callbackinforeturnable0) {
      callbackinforeturnable0.setReturnValue(String.format("%d.%d.%d.%d+ichor", getMajorVersion(), getMinorVersion(), getRevisionVersion(), getBuildVersion()));
   }

   @Inject(method = "startVersionCheck", at = @At("HEAD"), cancellable = true)
   private static void ichor$nullStartVersionCheck(CallbackInfo callback0) {
      callback0.cancel();
   }
}
