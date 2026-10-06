package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.optifine.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Config.class)
public abstract class ConfigMixin {
   @Shadow
   public static Minecraft minecraft;

   @Overwrite
   public static void startVersionCheckThread() {
   }

   @Inject(method = "updateFramebufferSize", at = @At("RETURN"))
   private static void lunar$webosr$resize(CallbackInfo var0) {
      if (DriverViewLegacy.method21() != null) {
         DriverViewLegacy.method21().method3(minecraft.getFramebuffer().framebufferWidth, minecraft.getFramebuffer().framebufferHeight);
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "openWebLink", at = @At("HEAD"), cancellable = true)
   private static void lunar$useLauncherOpenUrl(URI var0, CallbackInfoReturnable<Boolean> var1) {
      try {
         if (ThreadModuleDump61.method9(var0.toURL(), Initiator.INITIATOR_UNSPECIFIED)) {
            var1.setReturnValue(true);
         }
      } catch (Exception var3) {
      }
   }

   @WrapMethod(method = "getDefiningResourcePack")
   private static IResourcePack lunar$skipMissingField(ResourceLocation var0, Operation<IResourcePack> var1) {
      try {
         return (IResourcePack)var1.call(new Object[]{var0});
      } catch (NoSuchFieldError var3) {
         return null;
      }
   }

   @Inject(method = "dbg", at = @At("HEAD"), cancellable = true)
   private static void lunar$rewindDoNotLogWhenReplaying(CallbackInfo var0) {
      if (ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method40() != null
         && ThreadModuleDump63.method4().method40().method85().method19()) {
         var0.cancel();
      }
   }
}
