package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.RenderPlayerBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(RenderPlayer.class)
public abstract class RenderPlayerMixin extends RendererLivingEntity<AbstractClientPlayer> implements RenderPlayerBridge {
   public RenderPlayerMixin() {
   }

   @Shadow
   public abstract ModelPlayer getMainModel();

   @Override
   public ModelPlayerBridge bridge$getMainModel() {
      return (ModelPlayerBridge)this.getMainModel();
   }

   @VersionGate(min = 1)
   @Inject(
      method = "setModelVisibilities(Lnet/minecraft/client/entity/AbstractClientPlayer;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isSneaking()Z")
   )
   private void impl$onSetModelVisibilities(CallbackInfo callback1) {
      this.getMainModel().isRiding = false;
   }

   @Override
   public void bridge$renderEquippedItems(Bridge5_11 bridge5_111, float value2) {
   }
}
