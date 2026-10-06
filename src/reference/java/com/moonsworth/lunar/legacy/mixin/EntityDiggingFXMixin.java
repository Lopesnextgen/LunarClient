package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.EntityFXBridge;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import javax.annotation.Nullable;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDiggingFX.class)
public class EntityDiggingFXMixin {
   public EntityDiggingFXMixin() {
   }

   @Inject(
      method = {
            "renderParticle$v1_7(Lnet/minecraft/client/renderer/Tessellator;FFFFFF)V",
            "renderParticle$v1_8(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V"
      },
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$cancelParticleRendering(CallbackInfo callback1) {
      ParticleStyle particlechildmod2 = this.lunar$getConfig();
      if (particlechildmod2 != null && particlechildmod2.method14()) {
         callback1.cancel();
      }
   }

   @VersionGate(0)
   @WrapOperation(
      method = "renderParticle$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorOpaque_F$v1_7(FFF)V")
   )
   private void lunar$setColors(Tessellator tessellator1, float value2, float value3, float value4, Operation<Void> operation5) {
      ParticleStyle particlechildmod6 = this.lunar$getConfig();
      if (particlechildmod6 == null) {
         operation5.call(new Object[]{tessellator1, value2, value3, value4});
      } else {
         tessellator1.setColorRGBA_F$v1_7(particlechildmod6.applyRed(value2), particlechildmod6.applyGreen(value3), particlechildmod6.applyBlue(value4), particlechildmod6.applyAlpha(1.0F));
      }
   }

   @VersionGate(min = 1)
   @WrapOperation(
      method = "renderParticle$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;color$v1_8(FFFF)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$setColors(WorldRenderer worldrenderer1, float value2, float value3, float value4, float value5, Operation<WorldRenderer> operation6) {
      ParticleStyle particlechildmod7 = this.lunar$getConfig();
      return particlechildmod7 == null
         ? (WorldRenderer)operation6.call(new Object[]{worldrenderer1, value2, value3, value4, value5})
         : (WorldRenderer)operation6.call(new Object[]{worldrenderer1, particlechildmod7.method7(value2), particlechildmod7.method8(value3), particlechildmod7.method9(value4), particlechildmod7.method10(value5)});
   }

   @ModifyVariable(method = {"renderParticle$v1_7", "renderParticle$v1_8"}, at = @At("STORE"), ordinal = 10)
   private float lunar$modifyScale(float value1) {
      ParticleStyle particlechildmod2 = this.lunar$getConfig();
      return particlechildmod2 != null ? particlechildmod2.applyScale(value1) : value1;
   }

   @Unique
   @Nullable
   private ParticleStyle lunar$getConfig() {
      ParticleStyle particlechildmod1 = Ref.method4().method40().method22().method7(((EntityFXBridge)this).bridge$getParticleType());
      return particlechildmod1 != null && particlechildmod1.isActive() ? particlechildmod1 : null;
   }
}
