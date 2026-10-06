package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.ModelRendererAttachable;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(ModelPlayer.class)
public class ModelPlayerLayerMixin {
   public ModelPlayerLayerMixin() {
   }

   @Inject(method = "<init>(FZ)V", at = @At("TAIL"))
   private void lunar$constructor(float value1, boolean flag2, CallbackInfo callback3) {
      ModelPlayer modelplayer4 = (ModelPlayer)this;
      if (modelplayer4.bipedBody instanceof ModelRendererAttachable mixincore45) {
         mixincore45.lunar$attach(modelplayer4.bipedBodyWear);
      }

      if (modelplayer4.bipedLeftArm instanceof ModelRendererAttachable mixincore47) {
         mixincore47.lunar$attach(modelplayer4.bipedLeftArmwear);
      }

      if (modelplayer4.bipedRightArm instanceof ModelRendererAttachable mixincore48) {
         mixincore48.lunar$attach(modelplayer4.bipedRightArmwear);
      }

      if (modelplayer4.bipedLeftLeg instanceof ModelRendererAttachable mixincore49) {
         mixincore49.lunar$attach(modelplayer4.bipedLeftLegwear);
      }

      if (modelplayer4.bipedRightLeg instanceof ModelRendererAttachable mixincore410) {
         mixincore410.lunar$attach(modelplayer4.bipedRightLegwear);
      }
   }

   @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V"), cancellable = true)
   private void lunar$render(Entity entity1, float value2, float value3, float value4, float value5, float value6, float value7, CallbackInfo callback8) {
      callback8.cancel();
   }
}
