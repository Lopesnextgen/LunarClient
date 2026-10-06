package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.minecraft.EnumFacingBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EnumFacing.class)
public class EnumFacingMixin implements EnumFacingBridge {
   @Final
   @Shadow
   public int index;
   @Final
   @Shadow
   public int horizontalIndex;

   public EnumFacingMixin() {
   }

   public int bridge$index() {
      return this.index;
   }

   public int bridge$horizontalIndex() {
      if (Ref.MC_VERSION >= 1) {
         return this.horizontalIndex;
      }

      return switch (this.index) {
         case 2 -> 2;
         case 3 -> 0;
         case 4 -> 1;
         case 5 -> 3;
         default -> -1;
      };
   }
}
