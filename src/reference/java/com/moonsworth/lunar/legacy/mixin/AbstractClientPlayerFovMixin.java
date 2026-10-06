package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.SkinLoadedEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.FovRenderEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(min = 1)
@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerFovMixin {
   @Shadow
   public NetworkPlayerInfo playerInfo;

   @Shadow
   public abstract ResourceLocation getLocationSkin();

   @Shadow
   public abstract NetworkPlayerInfo getPlayerInfo();

   @Annotation2(min = 1)
   @Inject(method = "getFovModifier$v1_8", at = @At("TAIL"), cancellable = true)
   private void lunar$getFovModifier(CallbackInfoReturnable<Float> var1) {
      FovRenderEvent var2 = ClientEventBus.method29().method12(FovRenderEvent.class, () -> new FovRenderEvent((Float)var1.getReturnValue()));
      if (var2 != null) {
         if (var2.isCancelled()) {
            var1.setReturnValue(1.0F);
         } else {
            var1.setReturnValue(var2.method1());
         }
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "onSkinAvailable$v1_7", at = @At("TAIL"))
   private void lunar$onSkinAvailable(CallbackInfo var1) {
      ClientEventBus.method29().method12(SkinLoadedEvent.class, () -> new SkinLoadedEvent((ResourceLocationBridge)this.getLocationSkin()));
   }

   @Overwrite
   public boolean isSpectator() {
      NetworkPlayerInfo var1 = this.playerInfo;
      if (var1 == null) {
         var1 = this.getPlayerInfo();
      }

      if (this.playerInfo != null) {
         return ThreadModuleDump63.MC_VERSION >= 5
            ? var1.getGameType() == GameType.SPECTATOR
            : var1.getGameType() == net.minecraft.world.WorldSettings.GameType.SPECTATOR;
      } else {
         return false;
      }
   }
}
