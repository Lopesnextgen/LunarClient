package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@VersionGate(min = 1)
@Mixin(EntityArmorStand.class)
public abstract class EntityArmorStandRewindMixin {
   public EntityArmorStandRewindMixin() {
   }

   @WrapWithCondition(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;onUpdate()V"))
   private boolean lunar$rewindPreventUselessUpdate(EntityLivingBase entity1) {
      return !entity1.isInvisible() ? true : !Ref.method4().method40().method85().method17(arg0 -> arg0.method41().method18() / 50L > 20L);
   }
}
