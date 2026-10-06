package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.tileentity.TileEntityChestBridge;
import net.minecraft.tileentity.TileEntityChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityChest.class)
public class TileEntityChestMixin implements TileEntityChestBridge {
   @Shadow
   public float lidAngle;

   public TileEntityChestMixin() {
   }

   public boolean bridge$isVisuallyOpen() {
      return this.lidAngle != 0.0F;
   }
}
