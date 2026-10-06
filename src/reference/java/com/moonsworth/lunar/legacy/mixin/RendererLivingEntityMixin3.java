package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Annotation2(min = 1)
@Mixin(RendererLivingEntity.class)
public class RendererLivingEntityMixin3 {
   @Annotation2(max = 0)
   @Redirect(method = "doRender", at = @At(value = "INVOKE", target = "shadersmod/client/Shaders.setEntityColor (FFFF)V"))
   private void lunar$applyHitColorOverride_v1_7(float var1, float var2, float var3, float var4) {
      this.lunar$applyHitColorOverride(var1, var2, var3, var4);
   }

   @Annotation2(min = 1)
   @Redirect(method = "setBrightness", at = @At(value = "INVOKE", target = "net/optifine/shaders/Shaders.setEntityColor (FFFF)V"))
   private void lunar$applyHitColorOverride_v1_8(float var1, float var2, float var3, float var4) {
      this.lunar$applyHitColorOverride(var1, var2, var3, var4);
   }

   private void lunar$applyHitColorOverride(float var1, float var2, float var3, float var4) {
      if (var1 == 1.0F && var2 == 0.0F && var3 == 0.0F && var4 == 0.3F) {
         HitColor var5 = ThreadModuleDump63.method4().method40().method14();
         Shaders.setEntityColor(var5.method4(var1), var5.method5(var2), var5.method6(var3), var5.method7(var4));
      } else {
         Shaders.setEntityColor(var1, var2, var3, var4);
      }
   }
}
