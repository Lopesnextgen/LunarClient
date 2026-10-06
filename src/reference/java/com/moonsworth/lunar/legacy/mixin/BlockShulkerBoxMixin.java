package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3Extension3;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.item.EnumDyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 5)
@Mixin(BlockShulkerBox.class)
public abstract class BlockShulkerBoxMixin implements Bridge3Extension3 {
   @Final
   @Shadow
   public EnumDyeColor color;

   @Override
   public int bridge$getColor() {
      if (this.color == null) {
         return -6986091;
      }

      int var1 = this.color.getColorValue$v1_12();
      return var1 | 0xFF000000;
   }
}
