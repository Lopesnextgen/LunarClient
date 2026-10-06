package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionEffect.class)
public abstract class PotionEffectNullFixMixin implements PotionEffectBridge {
   public PotionEffectNullFixMixin() {
   }

   @WrapOperation(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/Potion;isReady(II)Z"))
   private boolean lunar$fixNullPotion(Potion potion1, int value, int value2, Operation<Boolean> operation4) {
      return potion1 == null ? false : (Boolean)operation4.call(new Object[]{potion1, value, value2});
   }
}
