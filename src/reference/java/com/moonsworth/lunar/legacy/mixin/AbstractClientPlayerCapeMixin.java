package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.NetworkPlayerInfoBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
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
public abstract class AbstractClientPlayerCapeMixin implements Bridge5_11 {
   @Shadow
   public ResourceLocation locationOfCape;
   @Shadow
   public ResourceLocation locationCape$v1_7;

   public AbstractClientPlayerCapeMixin() {
   }

   @Shadow
   public abstract NetworkPlayerInfo getPlayerInfo();

   @Inject(method = "setLocationOfCape", at = @At("HEAD"), cancellable = true, require = 1)
   private void lunar$setLocationOfCape(ResourceLocation location1, CallbackInfo callback2) {
      callback2.cancel();
      Ref.method3().bridge$submit(() -> this.bridge$setCapeLocation((ResourceLocationBridge)location1));
      this.locationOfCape = location1;
   }

   public boolean bridge$isOptifineCape() {
      return this.locationOfCape != null;
   }

   @Nullable
   public ResourceLocationBridge bridge$getCapeLocation() {
      if (this.locationOfCape != null) {
         return (ResourceLocationBridge)this.locationOfCape;
      } else if (Ref.MC_VERSION >= 1 && this.getPlayerInfo() instanceof NetworkPlayerInfoBridge bridge_301) {
         return bridge_301.bridge$getCapeLocation();
      } else {
         return Ref.MC_VERSION <= 0 ? (ResourceLocationBridge)this.locationCape$v1_7 : null;
      }
   }
}
