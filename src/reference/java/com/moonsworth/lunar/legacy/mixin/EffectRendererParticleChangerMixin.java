package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.EntityFXBridge;
import com.moonsworth.lunar.bridge.particle.SimpleParticleData;
import com.moonsworth.lunar.bridge.particle.LegacyParticleType;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderParticle;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EffectRenderer.class)
public abstract class EffectRendererParticleChangerMixin {
   @Final
   @Shadow
   public Map<Integer, IParticleFactory> particleTypes;
   @Shadow
   public World world;

   public EffectRendererParticleChangerMixin() {
   }

   @Shadow
   public abstract void addEffect(EntityFX entityfx1);

   @VersionGate(min = 1)
   @Inject(method = "registerVanillaParticles$v1_8", at = @At("TAIL"))
   private void lunar$registerVanillaParticles(CallbackInfo callback1) {
      for (int index3 : this.particleTypes.keySet()) {
         LegacyParticleType horsestatstype4 = LegacyParticleType.getParticleFromId(index3);
         ParticleType horsestatstype25 = horsestatstype4.asModernParticle();
         horsestatstype25.setDataProvider(new SimpleParticleData(horsestatstype4.getParticleName(), horsestatstype4.getParticleID()));
      }

      ParticleType.populateRegistry();
   }

   @VersionGate(min = 1)
   @WrapOperation(
      method = "spawnEffectParticle$v1_8(IDDDDDD[I)Lnet/minecraft/client/particle/Particle;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEffect(Lnet/minecraft/client/particle/Particle;)V")
   )
   private void lunar$spawnEffectParticle(
      EffectRenderer effectrenderer1,
      EntityFX entityfx2,
      Operation<Void> operation3,
      @Local(argsOnly = true, ordinal = 0) int number4,
      @Local(argsOnly = true, ordinal = 0) double value5,
      @Local(argsOnly = true, ordinal = 1) double value7,
      @Local(argsOnly = true, ordinal = 2) double value9,
      @Local(argsOnly = true, ordinal = 3) double value11,
      @Local(argsOnly = true, ordinal = 4) double value13,
      @Local(argsOnly = true, ordinal = 5) double value15,
      @Local(argsOnly = true, ordinal = 0) int[] items17,
      @Local IParticleFactory iparticlefactory18
   ) {
      EntityFXBridge bridge4_1219 = (EntityFXBridge)entityfx2;
      bridge4_1219.bridge$setParticleType(ParticleType.getParticleFromId(number4));
      ParticleStyle particlechildmod20 = Ref.method4().method40().method22().method7(bridge4_1219.bridge$getParticleType());
      if (particlechildmod20 != null && particlechildmod20.isActive()) {
         ThreadLocalRandom threadlocalrandom21 = ThreadLocalRandom.current();
         int number22 = particlechildmod20.getParticleCount(threadlocalrandom21);
         if (number22 == 0) {
            return;
         }

         if (number22 == 1) {
            operation3.call(new Object[]{effectrenderer1, entityfx2});
            return;
         }

         for (int index23 = 0; index23 < number22; index23++) {
            double value24 = particlechildmod20.jitter(threadlocalrandom21, value5);
            double value26 = particlechildmod20.jitter(threadlocalrandom21, value7);
            double value28 = particlechildmod20.jitter(threadlocalrandom21, value9);
            double value30 = particlechildmod20.method13(threadlocalrandom21, value11);
            double value32 = particlechildmod20.method13(threadlocalrandom21, value13);
            double value34 = particlechildmod20.method13(threadlocalrandom21, value15);
            EntityFX entityfx36;
            if (Ref.MC_VERSION >= 5) {
               entityfx36 = iparticlefactory18.createParticle$v1_12(number4, this.world, value24, value26, value28, value30, value32, value34, items17);
            } else {
               entityfx36 = iparticlefactory18.getEntityFX(number4, this.world, value24, value26, value28, value30, value32, value34, items17);
            }

            if (entityfx36 != null) {
               ((EntityFXBridge)entityfx36).bridge$setParticleType(ParticleType.getParticleFromId(number4));
               this.addEffect(entityfx36);
            }
         }
      }

      operation3.call(new Object[]{effectrenderer1, entityfx2});
   }

   @VersionGate(min = 1)
   @Redirect(
      method = "renderParticles",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/particle/Particle;renderParticle$v1_8(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V"
      ),
      require = 0,
      expect = 0
   )
   private void lunar$renderParticle$v1_8(
      EntityFX entityfx1, WorldRenderer worldrenderer2, Entity entity3, float value4, float value5, float value6, float value7, float value8, float value9
   ) {
      EventRenderParticle highlightimpl2610 = (EventRenderParticle)LunarEventBus.method29().method12(EventRenderParticle.class, () -> new EventRenderParticle((EntityFXBridge)entityfx1));
      if (highlightimpl2610 == null || !highlightimpl2610.isCancelled()) {
         entityfx1.renderParticle(worldrenderer2, entity3, value4, value5, value6, value7, value8, value9);
      }
   }

   @VersionGate(max = 0)
   @Redirect(
      method = "renderParticles",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/Particle;renderParticle$v1_7(Lnet/minecraft/client/renderer/Tessellator;FFFFFF)V")
   )
   private void lunar$renderParticle$v1_7(EntityFX entityfx1, Tessellator tessellator2, float value3, float value4, float value5, float value6, float value7, float value8) {
      EventRenderParticle highlightimpl269 = (EventRenderParticle)LunarEventBus.method29().method12(EventRenderParticle.class, () -> new EventRenderParticle((EntityFXBridge)entityfx1));
      if (highlightimpl269 == null || !highlightimpl269.isCancelled()) {
         entityfx1.renderParticle(tessellator2, value3, value4, value5, value6, value7, value8);
      }
   }

   @VersionGate(max = 0)
   @Inject(method = "addBlockDestroyEffects$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$addBlockDestroyEffects$head(CallbackInfo callback1) {
      ParticleStyle particlechildmod2 = Ref.method4().method40().method22().method7(ParticleType.BLOCK);
      if (particlechildmod2 != null && particlechildmod2.method14()) {
         callback1.cancel();
      }
   }

   @VersionGate(max = 0)
   @Inject(method = "addBlockHitEffects$v1_7(IIII)V", at = @At("HEAD"), cancellable = true)
   private void lunar$addBlockHitEffects$head(CallbackInfo callback1) {
      ParticleStyle particlechildmod2 = Ref.method4().method40().method22().method7(ParticleType.BLOCK);
      if (particlechildmod2 != null && particlechildmod2.method14()) {
         callback1.cancel();
      }
   }

   @VersionGate(min = 1)
   @ModifyExpressionValue(
      method = {
            "addBlockHitEffects$v1_8(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V",
            "addBlockDestroyEffects$v1_8(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)V"
      },
      at = @At(value = "NEW", target = "net/minecraft/client/particle/ParticleDigging")
   )
   private EntityDiggingFX lunar$setIdOfBreakingParticles$v1_8(EntityDiggingFX entitydiggingfx1) {
      ((EntityFXBridge)entitydiggingfx1).bridge$setParticleType(ParticleType.getParticleFromId(LegacyParticleType.BLOCK_DUST.getParticleID()));
      return entitydiggingfx1;
   }

   @VersionGate(max = 0)
   @ModifyExpressionValue(
      method = {"addBlockHitEffects$v1_7", "addBlockDestroyEffects$v1_7"},
      at = @At(value = "NEW", target = "net/minecraft/client/particle/ParticleDigging")
   )
   private EntityDiggingFX lunar$setIdOfBreakingParticles$v1_7(EntityDiggingFX entitydiggingfx1) {
      ((EntityFXBridge)entitydiggingfx1).bridge$setParticleType(ParticleType.getParticleFromId(LegacyParticleType.BLOCK_DUST.getParticleID()));
      return entitydiggingfx1;
   }

   @Inject(method = "addEffect", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventSpawnUselessParticles(EntityFX entityfx1, CallbackInfo callback2) {
      if (Ref.method4().method40().method85().method17(arg1x -> arg1x.method41().method18() / 50L > entityfx1.particleMaxAge - entityfx1.particleAge)) {
         callback2.cancel();
      }
   }
}
