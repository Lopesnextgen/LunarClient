package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityCreeperBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityCreeper.class)
public abstract class EntityCreeperMixin extends Entity implements EntityCreeperBridge {
   @Shadow
   public int fuseTime;
   @Shadow
   public int timeSinceIgnited;
   @Shadow
   public int lastActiveTime;

   public EntityCreeperMixin() {
   }

   public int bridge$getMaxSwell() {
      return this.fuseTime;
   }

   public int bridge$getSwell() {
      return this.timeSinceIgnited;
   }

   public void bridge$setSwell(int number1) {
      this.timeSinceIgnited = number1;
   }

   public void bridge$setOldSwell(int number1) {
      this.lastActiveTime = number1;
   }

   public void bridge$setPowered(boolean flag1) {
      if (Ref.MC_VERSION >= 5) {
         this.dataManager.set(EntityCreeper.POWERED$v1_12, flag1);
      } else if (Ref.MC_VERSION >= 1) {
         this.dataManager.updateObject(17, Byte.valueOf((byte)(flag1 ? 1 : 0)));
      } else {
         this.dataManager.updateObject(17, Byte.valueOf((byte)(flag1 ? 1 : 0)));
      }
   }

   public void bridge$setIgnited(boolean flag1) {
      if (Ref.MC_VERSION >= 5) {
         this.dataManager.set(EntityCreeper.IGNITED$v1_12, flag1);
      } else if (Ref.MC_VERSION >= 1) {
         this.dataManager.updateObject(18, Byte.valueOf((byte)(flag1 ? 1 : 0)));
      } else {
         this.dataManager.updateObject(18, Byte.valueOf((byte)(flag1 ? 1 : 0)));
      }
   }
}
