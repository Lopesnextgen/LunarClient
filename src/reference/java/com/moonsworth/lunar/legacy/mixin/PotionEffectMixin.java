package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionEffect.class)
public abstract class PotionEffectMixin implements PotionEffectBridge {
   @Shadow
   public int potionID;
   @Final
   @Shadow
   public Potion potion$v1_12;
   @Unique
   private int lunar$maxDuration;

   public PotionEffectMixin() {
   }

   @Shadow
   public abstract boolean getIsPotionDurationMax();

   @Shadow
   public abstract int getDuration();

   @Shadow
   public abstract int getAmplifier();

   @Shadow
   public abstract String getEffectName();

   @Shadow
   public abstract boolean getIsAmbient();

   @Inject(method = "deincrementDuration()I", at = @At("HEAD"))
   private void lunar$trackMaxDuration(CallbackInfoReturnable<Integer> callbackinforeturnable1) {
      this.lunar$maxDuration = Math.max(this.lunar$maxDuration, this.getDuration());
   }

   @Inject(method = "combine(Lnet/minecraft/potion/PotionEffect;)V", at = @At("HEAD"))
   private void lunar$resetMaxDuration(PotionEffect effect1, CallbackInfo callback2) {
      if (effect1.getAmplifier() > this.getAmplifier()) {
         this.lunar$maxDuration = 0;
      }
   }

   public boolean bridge$getIsPotionDurationMax() {
      return this.getIsPotionDurationMax();
   }

   public int bridge$getPotionID() {
      return Ref.MC_VERSION == 5 ? Potion.getIdFromPotion$v1_12(this.potion$v1_12) : this.potionID;
   }

   public PotionBridge bridge$getPotion() {
      return Ref.MC_VERSION == 5 ? (PotionBridge)this.potion$v1_12 : (PotionBridge)Potion.potionTypes[this.potionID];
   }

   public float bridge$getDuration() {
      return this.getDuration();
   }

   public int bridge$getMaxDuration() {
      return this.lunar$maxDuration;
   }

   public int bridge$getAmplifier() {
      return this.getAmplifier();
   }

   public String bridge$getEffectName() {
      return this.getEffectName();
   }

   public boolean bridge$getIsAmbient() {
      return this.getIsAmbient();
   }
}
