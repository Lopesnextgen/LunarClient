package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntitySkeletonBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.entity.monster.EntitySkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntitySkeleton.class)
public abstract class EntitySkeletonMixin implements EntitySkeletonBridge {
   public EntitySkeletonMixin() {
   }

   @Shadow
   public abstract int getSkeletonType();

   public boolean bridge$isWitherSkeleton() {
      return Ref.MC_VERSION < 5 ? this.getSkeletonType() == 1 : false;
   }
}
