package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import javax.annotation.Nullable;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFootStepFX.class)
public class EntityFootStepFXMixin {
   public EntityFootStepFXMixin() {
   }

   @VersionGate(max = 0)
   @Redirect(
      method = "renderParticle$v1_7(Lnet/minecraft/client/renderer/Tessellator;FFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_F$v1_7(FFFF)V")
   )
   private void lunar$customFootprintParticleColor(Tessellator tessellator1, float value2, float value3, float value4, float value5) {
      ParticleStyle particlechildmod6 = this.lunar$getConfig();
      if (particlechildmod6 != null) {
         value2 = particlechildmod6.applyRed(value2);
         value3 = particlechildmod6.applyGreen(value3);
         value4 = particlechildmod6.applyBlue(value4);
         value5 = particlechildmod6.applyAlpha(value5);
      }

      tessellator1.setColorRGBA_F$v1_7(value2, value3, value4, value5);
   }

   @VersionGate(min = 1)
   @Redirect(
      method = "renderParticle$v1_8(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;color$v1_8(FFFF)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$customFootprintParticleColor$v1_8(WorldRenderer worldrenderer1, float value2, float value3, float value4, float value5) {
      ParticleStyle particlechildmod6 = this.lunar$getConfig();
      if (particlechildmod6 != null) {
         value2 = particlechildmod6.applyRed(value2);
         value3 = particlechildmod6.applyGreen(value3);
         value4 = particlechildmod6.applyBlue(value4);
         value5 = particlechildmod6.applyAlpha(value5);
      }

      return worldrenderer1.color(value2, value3, value4, value5);
   }

   @VersionGate(max = 0)
   @Redirect(
      method = "renderParticle$v1_7(Lnet/minecraft/client/renderer/Tessellator;FFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertexWithUV$v1_7(DDDDD)V")
   )
   private void lunar$scaleFootprintParticle(Tessellator tessellator1, double value2, double value4, double value6, double value8, double value10) {
      Entity entity12 = (Entity)this;
      float value13 = (float)(entity12.posX - EntityFX.interpPosX);
      float value14 = (float)(entity12.posY - EntityFX.interpPosY);
      float value15 = (float)(entity12.posZ - EntityFX.interpPosZ);
      double value16 = value2 - value13;
      double value18 = value6 - value15;
      float value20 = this.lunar$getScale();
      tessellator1.addVertexWithUV$v1_7(value13 + value16 * value20, value14, value15 + value18 * value20, value8, value10);
   }

   @VersionGate(1)
   @Redirect(
      method = "renderParticle$v1_8(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;pos$v1_8(DDD)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$scaleFootprintParticle$v1_8(WorldRenderer worldrenderer1, double value2, double value4, double value6) {
      Entity entity8 = (Entity)this;
      float value9 = (float)(entity8.posX - EntityFX.interpPosX);
      float value10 = (float)(entity8.posY - EntityFX.interpPosY);
      float value11 = (float)(entity8.posZ - EntityFX.interpPosZ);
      double value12 = value2 - value9;
      double value14 = value6 - value11;
      float value16 = this.lunar$getScale();
      return worldrenderer1.pos(value9 + value12 * value16, value10, value11 + value14 * value16);
   }

   @VersionGate(min = 5)
   @Redirect(
      method = "renderParticle$v1_8(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;pos$v1_8(DDD)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$scaleFootprintParticle$v1_12(WorldRenderer worldrenderer1, double value2, double value4, double value6) {
      EntityFX entityfx8 = (EntityFX)this;
      float value9 = (float)(entityfx8.posX$v1_12 - EntityFX.interpPosX);
      float value10 = (float)(entityfx8.posY$v1_12 - EntityFX.interpPosY);
      float value11 = (float)(entityfx8.posZ$v1_12 - EntityFX.interpPosZ);
      double value12 = value2 - value9;
      double value14 = value6 - value11;
      float value16 = this.lunar$getScale();
      return worldrenderer1.pos(value9 + value12 * value16, value10, value11 + value14 * value16);
   }

   @Inject(
      method = {
            "renderParticle$v1_7(Lnet/minecraft/client/renderer/Tessellator;FFFFFF)V",
            "renderParticle$v1_8(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V"
      },
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$cancelFootstepParticleRendering(CallbackInfo callback1) {
      ParticleStyle particlechildmod2 = this.lunar$getConfig();
      if (particlechildmod2 != null && particlechildmod2.method14()) {
         callback1.cancel();
      }
   }

   @Unique
   @Nullable
   private ParticleStyle lunar$getConfig() {
      ParticleStyle particlechildmod1 = Ref.method4().method40().method22().method7(ParticleType.FOOTSTEP);
      return particlechildmod1 != null && particlechildmod1.isActive() ? particlechildmod1 : null;
   }

   @Unique
   private float lunar$getScale() {
      ParticleStyle particlechildmod1 = this.lunar$getConfig();
      return particlechildmod1 == null ? 1.0F : particlechildmod1.method11(1.0F);
   }
}
