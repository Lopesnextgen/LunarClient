package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.LightOverlayTrackerBridge;
import com.moonsworth.lunar.bridge.Bridge9_7;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.WorldRenderer;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(0)
@Mixin(WorldRenderer.class)
public class WorldRendererMixin implements Bridge9_7 {
   @Shadow
   public int posX$v1_7;
   @Shadow
   public int posY$v1_7;
   @Shadow
   public int posZ$v1_7;
   @Unique
   private final Vector3i bridge$pos = new Vector3i();

   public WorldRendererMixin() {
   }

   public Horsestats20Extension2 bridge$origin() {
      this.bridge$pos.set(this.posX$v1_7, this.posY$v1_7, this.posZ$v1_7);
      return (Horsestats20Extension2)this.bridge$pos;
   }

   @Nullable
   public LightOverlayTrackerBridge bridge$lightOverlayTracker() {
      return null;
   }

   @Inject(method = "updateRenderer$v1_7", at = @At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z"))
   private void lunar$onRebuild(CallbackInfo callback1) {
      Ref.method4().method40().method92().setNeedsUpdate(true);
   }
}
