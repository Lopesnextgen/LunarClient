package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.MixinCore4;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(ModelPlayer.class)
public class ModelPlayerMixin2 {
   @Inject(method = "<init>(FZ)V", at = @At("TAIL"))
   private void lunar$constructor(float var1, boolean var2, CallbackInfo var3) {
      ModelPlayer var4 = (ModelPlayer)this;
      if (var4.bipedBody instanceof MixinCore4 var5) {
         var5.lunar$attach(var4.bipedBodyWear);
      }

      if (var4.bipedLeftArm instanceof MixinCore4 var7) {
         var7.lunar$attach(var4.bipedLeftArmwear);
      }

      if (var4.bipedRightArm instanceof MixinCore4 var8) {
         var8.lunar$attach(var4.bipedRightArmwear);
      }

      if (var4.bipedLeftLeg instanceof MixinCore4 var9) {
         var9.lunar$attach(var4.bipedLeftLegwear);
      }

      if (var4.bipedRightLeg instanceof MixinCore4 var10) {
         var10.lunar$attach(var4.bipedRightLegwear);
      }
   }

   @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V"), cancellable = true)
   private void lunar$render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7, CallbackInfo var8) {
      var8.cancel();
   }
}
