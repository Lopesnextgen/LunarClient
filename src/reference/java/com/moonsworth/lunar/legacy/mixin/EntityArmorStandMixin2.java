package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Annotation2(min = 1)
@Mixin(EntityArmorStand.class)
public abstract class EntityArmorStandMixin2 {
   @WrapWithCondition(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;onUpdate()V"))
   private boolean lunar$rewindPreventUselessUpdate(EntityLivingBase entity) {
      return !entity.isInvisible() ? true : !ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L);
   }
}
