package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.fog.Fog;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionEffect.class)
public abstract class PotionEffectMixin2 implements Fog {
   @WrapOperation(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/Potion;isReady(II)Z"))
   private boolean lunar$fixNullPotion(Potion potion, int value, int value2, Operation<Boolean> operation) {
      return potion == null ? false : (Boolean)operation.call(new Object[]{potion, value, value2});
   }
}
