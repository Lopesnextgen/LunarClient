package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@VersionGate(min = 1)
@Mixin(RendererLivingEntity.class)
public class RendererLivingEntityHitColorMixin {
   public RendererLivingEntityHitColorMixin() {
   }

   @VersionGate(max = 0)
   @Redirect(method = "doRender", at = @At(value = "INVOKE", target = "shadersmod/client/Shaders.setEntityColor (FFFF)V"))
   private void lunar$applyHitColorOverride_v1_7(float value1, float value2, float value3, float value4) {
      this.lunar$applyHitColorOverride(value1, value2, value3, value4);
   }

   @VersionGate(min = 1)
   @Redirect(method = "setBrightness", at = @At(value = "INVOKE", target = "net/optifine/shaders/Shaders.setEntityColor (FFFF)V"))
   private void lunar$applyHitColorOverride_v1_8(float value1, float value2, float value3, float value4) {
      this.lunar$applyHitColorOverride(value1, value2, value3, value4);
   }

   private void lunar$applyHitColorOverride(float value1, float value2, float value3, float value4) {
      if (value1 == 1.0F && value2 == 0.0F && value3 == 0.0F && value4 == 0.3F) {
         HitColor hitcolor5 = Ref.method4().method40().method14();
         Shaders.setEntityColor(hitcolor5.method4(value1), hitcolor5.method5(value2), hitcolor5.method6(value3), hitcolor5.method7(value4));
      } else {
         Shaders.setEntityColor(value1, value2, value3, value4);
      }
   }
}
