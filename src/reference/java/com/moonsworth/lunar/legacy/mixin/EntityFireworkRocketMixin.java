package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityFireworkRocketBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityFireworkRocket.class)
public abstract class EntityFireworkRocketMixin implements EntityFireworkRocketBridge {
   @Shadow
   @Nullable
   public EntityLivingBase boostedEntity$v1_12;

   public EntityFireworkRocketMixin() {
   }

   @VersionGate(min = 5)
   @Nullable
   public EntityLivingBridge bridge$getAttachedToEntity() {
      return (EntityLivingBridge)this.boostedEntity$v1_12;
   }
}
