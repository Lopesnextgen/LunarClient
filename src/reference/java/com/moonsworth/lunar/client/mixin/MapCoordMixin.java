package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter$Type;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_2;
import net.minecraft.world.storage.MapData.MapCoord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MapCoord.class)
public class MapCoordMixin implements Itemcounter_2 {
   @Shadow
   public byte centerX;
   @Shadow
   public byte centerZ;
   @Shadow
   public byte iconRotation;

   @Override
   public byte bridge$getX() {
      return this.centerX;
   }

   @Override
   public byte bridge$getY() {
      return this.centerZ;
   }

   @Override
   public byte bridge$getRot() {
      return this.iconRotation;
   }

   @Override
   public void bridge$setX(byte var1) {
      this.centerX = var1;
   }

   @Override
   public void bridge$setY(byte var1) {
      this.centerZ = var1;
   }

   @Override
   public void bridge$setRot(byte var1) {
      this.iconRotation = var1;
   }

   @Override
   public Itemcounter$Type bridge$getDecorationType() {
      throw new UnsupportedOperationException("Not available on 1.7");
   }
}
