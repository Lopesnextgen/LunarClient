package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge_30;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin3 implements Bridge5_11 {
   @Shadow
   public ResourceLocation locationOfCape;
   @Shadow
   public ResourceLocation locationCape$v1_7;

   @Shadow
   public abstract NetworkPlayerInfo getPlayerInfo();

   @Inject(method = "setLocationOfCape", at = @At("HEAD"), cancellable = true, require = 1)
   private void lunar$setLocationOfCape(ResourceLocation var1, CallbackInfo var2) {
      var2.cancel();
      ThreadModuleDump63.method3().bridge$submit(() -> this.bridge$setCapeLocation((ResourceLocationBridge)var1));
      this.locationOfCape = var1;
   }

   public boolean bridge$isOptifineCape() {
      return this.locationOfCape != null;
   }

   @Nullable
   public ResourceLocationBridge bridge$getCapeLocation() {
      if (this.locationOfCape != null) {
         return (ResourceLocationBridge)this.locationOfCape;
      } else if (ThreadModuleDump63.MC_VERSION >= 1 && this.getPlayerInfo() instanceof Bridge_30 var1) {
         return var1.bridge$getCapeLocation();
      } else {
         return ThreadModuleDump63.MC_VERSION <= 0 ? (ResourceLocationBridge)this.locationCape$v1_7 : null;
      }
   }
}
