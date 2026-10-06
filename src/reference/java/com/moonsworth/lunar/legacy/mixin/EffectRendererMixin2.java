package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge4_12;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsHandler;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ParticleRenderEvent;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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
public abstract class EffectRendererMixin2 {
   @Final
   @Shadow
   public Map<Integer, IParticleFactory> particleTypes;
   @Shadow
   public World world;

   @Shadow
   public abstract void addEffect(EntityFX var1);

   @Annotation2(min = 1)
   @Inject(method = "registerVanillaParticles$v1_8", at = @At("TAIL"))
   private void lunar$registerVanillaParticles(CallbackInfo var1) {
      for (int var3 : this.particleTypes.keySet()) {
         HorsestatsType var4 = HorsestatsType.getParticleFromId(var3);
         HorsestatsType2 var5 = var4.asModernParticle();
         var5.setDataProvider(new HorsestatsHandler(var4.getParticleName(), var4.getParticleID()));
      }

      HorsestatsType2.populateRegistry();
   }

   @Annotation2(min = 1)
   @WrapOperation(
      method = "spawnEffectParticle$v1_8(IDDDDDD[I)Lnet/minecraft/client/particle/Particle;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEffect(Lnet/minecraft/client/particle/Particle;)V")
   )
   private void lunar$spawnEffectParticle(
      EffectRenderer var1,
      EntityFX var2,
      Operation<Void> var3,
      @Local(argsOnly = true, ordinal = 0) int var4,
      @Local(argsOnly = true, ordinal = 0) double var5,
      @Local(argsOnly = true, ordinal = 1) double var7,
      @Local(argsOnly = true, ordinal = 2) double var9,
      @Local(argsOnly = true, ordinal = 3) double var11,
      @Local(argsOnly = true, ordinal = 4) double var13,
      @Local(argsOnly = true, ordinal = 5) double var15,
      @Local(argsOnly = true, ordinal = 0) int[] var17,
      @Local IParticleFactory var18
   ) {
      Bridge4_12 var19 = (Bridge4_12)var2;
      var19.bridge$setParticleType(HorsestatsType2.getParticleFromId(var4));
      ParticleStyle var20 = ThreadModuleDump63.method4().method40().method22().method7(var19.bridge$getParticleType());
      if (var20 != null && var20.isActive()) {
         ThreadLocalRandom var21 = ThreadLocalRandom.current();
         int var22 = var20.getParticleCount(var21);
         if (var22 == 0) {
            return;
         }

         if (var22 == 1) {
            var3.call(new Object[]{var1, var2});
            return;
         }

         for (int var23 = 0; var23 < var22; var23++) {
            double var24 = var20.jitter(var21, var5);
            double var26 = var20.jitter(var21, var7);
            double var28 = var20.jitter(var21, var9);
            double var30 = var20.method13(var21, var11);
            double var32 = var20.method13(var21, var13);
            double var34 = var20.method13(var21, var15);
            EntityFX var36;
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               var36 = var18.createParticle$v1_12(var4, this.world, var24, var26, var28, var30, var32, var34, var17);
            } else {
               var36 = var18.getEntityFX(var4, this.world, var24, var26, var28, var30, var32, var34, var17);
            }

            if (var36 != null) {
               ((Bridge4_12)var36).bridge$setParticleType(HorsestatsType2.getParticleFromId(var4));
               this.addEffect(var36);
            }
         }
      }

      var3.call(new Object[]{var1, var2});
   }

   @Annotation2(min = 1)
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
      EntityFX var1, WorldRenderer var2, Entity var3, float var4, float var5, float var6, float var7, float var8, float var9
   ) {
      ParticleRenderEvent var10 = (ParticleRenderEvent)ClientEventBus.method29().method12(ParticleRenderEvent.class, () -> new ParticleRenderEvent((Bridge4_12)var1));
      if (var10 == null || !var10.isCancelled()) {
         var1.renderParticle(var2, var3, var4, var5, var6, var7, var8, var9);
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderParticles",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/Particle;renderParticle$v1_7(Lnet/minecraft/client/renderer/Tessellator;FFFFFF)V")
   )
   private void lunar$renderParticle$v1_7(EntityFX var1, Tessellator var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      ParticleRenderEvent var9 = (ParticleRenderEvent)ClientEventBus.method29().method12(ParticleRenderEvent.class, () -> new ParticleRenderEvent((Bridge4_12)var1));
      if (var9 == null || !var9.isCancelled()) {
         var1.renderParticle(var2, var3, var4, var5, var6, var7, var8);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "addBlockDestroyEffects$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$addBlockDestroyEffects$head(CallbackInfo var1) {
      ParticleStyle var2 = ThreadModuleDump63.method4().method40().method22().method7(HorsestatsType2.BLOCK);
      if (var2 != null && var2.method14()) {
         var1.cancel();
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "addBlockHitEffects$v1_7(IIII)V", at = @At("HEAD"), cancellable = true)
   private void lunar$addBlockHitEffects$head(CallbackInfo var1) {
      ParticleStyle var2 = ThreadModuleDump63.method4().method40().method22().method7(HorsestatsType2.BLOCK);
      if (var2 != null && var2.method14()) {
         var1.cancel();
      }
   }

   @Annotation2(min = 1)
   @ModifyExpressionValue(
      method = {
            "addBlockHitEffects$v1_8(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V",
            "addBlockDestroyEffects$v1_8(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)V"
      },
      at = @At(value = "NEW", target = "net/minecraft/client/particle/ParticleDigging")
   )
   private EntityDiggingFX lunar$setIdOfBreakingParticles$v1_8(EntityDiggingFX var1) {
      ((Bridge4_12)var1).bridge$setParticleType(HorsestatsType2.getParticleFromId(HorsestatsType.BLOCK_DUST.getParticleID()));
      return var1;
   }

   @Annotation2(max = 0)
   @ModifyExpressionValue(
      method = {"addBlockHitEffects$v1_7", "addBlockDestroyEffects$v1_7"},
      at = @At(value = "NEW", target = "net/minecraft/client/particle/ParticleDigging")
   )
   private EntityDiggingFX lunar$setIdOfBreakingParticles$v1_7(EntityDiggingFX var1) {
      ((Bridge4_12)var1).bridge$setParticleType(HorsestatsType2.getParticleFromId(HorsestatsType.BLOCK_DUST.getParticleID()));
      return var1;
   }

   @Inject(method = "addEffect", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventSpawnUselessParticles(EntityFX var1, CallbackInfo var2) {
      if (ThreadModuleDump63.method4().method40().method85().method17(var1x -> var1x.method41().method18() / 50L > var1.particleMaxAge - var1.particleAge)) {
         var2.cancel();
      }
   }
}
