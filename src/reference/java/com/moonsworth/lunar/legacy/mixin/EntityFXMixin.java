package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moonsworth.lunar.bridge.Bridge4_12;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.ParticleFirework.OverlayFX;
import net.minecraft.client.particle.ParticleFirework.SparkFX;
import net.minecraft.client.particle.ParticleFirework.StarterFX;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFX.class)
public abstract class EntityFXMixin implements Bridge4_12 {
   @Unique
   private HorsestatsType2 lunar$particleType = null;
   @Unique
   private Vector4f lunar$colorBackup = null;
   @Unique
   private float lunar$scaleBackup = 1.0F;
   @Shadow
   public float particleRed;
   @Shadow
   public float particleGreen;
   @Shadow
   public float particleBlue;
   @Shadow
   public float particleAlpha;
   @Shadow
   public float particleScale;

   @Shadow
   public abstract float getRedColorF();

   @Shadow
   public abstract float getBlueColorF();

   @Shadow
   public abstract float getGreenColorF();

   @Annotation2(min = 5)
   @ModifyExpressionValue(method = "move$v1_12", at = @At(value = "FIELD", target = "Lnet/minecraft/client/particle/Particle;canCollide$v1_12:Z"))
   private boolean lunar$disableCollisionChecks(boolean var1) {
      return var1 && ThreadModuleDump63.method4().method41().method7().method26().get();
   }

   @Override
   public float bridge$getRed() {
      return this.getRedColorF();
   }

   @Override
   public float bridge$getGreen() {
      return this.getGreenColorF();
   }

   @Override
   public float bridge$getBlue() {
      return this.getBlueColorF();
   }

   @Override
   public float bridge$getAlpha() {
      return this.particleAlpha;
   }

   @Override
   public void bridge$setParticleType(HorsestatsType2 var1) {
      this.lunar$particleType = var1;
   }

   @Override
   public HorsestatsType2 bridge$getParticleType() {
      return this.lunar$particleType;
   }

   @Inject(method = {"renderParticle$v1_8", "renderParticle$v1_7"}, at = @At("HEAD"))
   private void lunar$onRender(CallbackInfo var1) {
      if (!this.bridge$isFirework() && this.lunar$particleType != null) {
         ParticleStyle var2 = ThreadModuleDump63.method4().method40().method22().method7(this.bridge$getParticleType());
         if (var2 != null && var2.isActive()) {
            this.lunar$colorBackup = new Vector4f(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
            this.lunar$scaleBackup = this.particleScale;
            this.particleRed = var2.applyRed(this.particleRed);
            this.particleGreen = var2.applyGreen(this.particleGreen);
            this.particleBlue = var2.applyBlue(this.particleBlue);
            this.particleAlpha = var2.applyAlpha(this.particleAlpha);
            this.particleScale = var2.applyScale(this.particleScale);
         }
      }
   }

   @Inject(method = {"renderParticle$v1_8", "renderParticle$v1_7"}, at = @At("TAIL"))
   private void lunar$onRenderPost(CallbackInfo var1) {
      if (this.lunar$colorBackup != null) {
         this.particleRed = this.lunar$colorBackup.x;
         this.particleGreen = this.lunar$colorBackup.y;
         this.particleBlue = this.lunar$colorBackup.z;
         this.particleAlpha = this.lunar$colorBackup.w;
         this.particleScale = this.lunar$scaleBackup;
         this.lunar$colorBackup = null;
      }
   }

   @Annotation2(min = 1)
   @Override
   public boolean bridge$isFirework() {
      EntityFX var1 = (EntityFX)this;
      return var1 instanceof StarterFX || var1 instanceof OverlayFX || var1 instanceof SparkFX;
   }
}
