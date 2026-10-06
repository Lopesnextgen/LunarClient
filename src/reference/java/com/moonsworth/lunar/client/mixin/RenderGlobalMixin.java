package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.EntityFXBridge;
import com.moonsworth.lunar.bridge.particle.ParticleData;
import com.moonsworth.lunar.bridge.particle.LegacyParticleType;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin implements Bridge14_3 {
   public RenderGlobalMixin() {
   }

   @Shadow
   public abstract EntityFX doSpawnParticle(String text1, double value2, double value4, double value6, double value8, double value10, double value12);

   @Inject(
      method = "spawnParticle",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/RenderGlobal;doSpawnParticle(Ljava/lang/String;DDDDDD)Lnet/minecraft/client/particle/EntityFX;"
      ),
      cancellable = true
   )
   private void lunar$spawnParticle$doSpawnParticle(
      String text1, double value2, double value4, double value6, double value8, double value10, double value12, CallbackInfo callback14
   ) {
      LegacyParticleType horsestatstype15 = LegacyParticleType.getParticleFromName(text1);
      if (horsestatstype15 != null) {
         ParticleStyle particlechildmod16 = Ref.method4().method40().method22().method7(horsestatstype15.asModernParticle());
         if (particlechildmod16 != null && particlechildmod16.isActive()) {
            ThreadLocalRandom threadlocalrandom17 = ThreadLocalRandom.current();
            int number18 = particlechildmod16.getParticleCount(threadlocalrandom17);
            if (number18 == 0) {
               callback14.cancel();
            }

            if (number18 <= 1) {
               return;
            }

            for (int index19 = 0; index19 < number18; index19++) {
               double value20 = particlechildmod16.jitter(threadlocalrandom17, value2);
               double value22 = particlechildmod16.jitter(threadlocalrandom17, value4);
               double value24 = particlechildmod16.jitter(threadlocalrandom17, value6);
               double value26 = particlechildmod16.method13(threadlocalrandom17, value8);
               double value28 = particlechildmod16.method13(threadlocalrandom17, value10);
               double value30 = particlechildmod16.method13(threadlocalrandom17, value12);
               this.doSpawnParticle(text1, value20, value22, value24, value26, value28, value30);
            }
         }
      }
   }

   @Inject(method = "doSpawnParticle", at = @At("RETURN"))
   private void lunar$doSpawnParticle(
      String text1, double value2, double value4, double value6, double value8, double value10, double value12, CallbackInfoReturnable<EntityFX> callbackinforeturnable14
   ) {
      EntityFX entityfx15 = (EntityFX)callbackinforeturnable14.getReturnValue();
      if (entityfx15 != null) {
         LegacyParticleType horsestatstype16 = LegacyParticleType.getParticleFromName(text1);
         if (horsestatstype16 != null) {
            ParticleData horsestats17 = horsestatstype16.asModernParticle().getDataProvider();
            if (horsestats17 != null) {
               int number18 = horsestats17.getId();
               ((EntityFXBridge)entityfx15).bridge$setParticleType(ParticleType.getParticleFromId(number18));
            }
         }
      }
   }
}
