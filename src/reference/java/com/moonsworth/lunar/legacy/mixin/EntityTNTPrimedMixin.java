package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityTNTPrimedBridge;
import net.minecraft.entity.item.EntityTNTPrimed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityTNTPrimed.class)
public abstract class EntityTNTPrimedMixin implements EntityTNTPrimedBridge {
   @Unique
   private int lunar$maximumFuse = 80;

   public EntityTNTPrimedMixin() {
   }

   public int bridge$getMaximumFuse() {
      return this.lunar$maximumFuse;
   }

   public void bridge$setMaximumFuse(int value) {
      this.lunar$maximumFuse = value;
   }
}
