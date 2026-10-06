package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter$Type;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.world.storage.MapDecoration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 5)
@Mixin(MapDecoration.class)
public class MapDecorationMixin implements Itemcounter_2 {
   @Shadow
   public byte x;
   @Shadow
   public byte y;
   @Shadow
   public byte rotation;
   @Final
   @Shadow
   public net.minecraft.world.storage.MapDecoration.Type type;

   @Override
   public byte bridge$getX() {
      return this.x;
   }

   @Override
   public byte bridge$getY() {
      return this.y;
   }

   @Override
   public byte bridge$getRot() {
      return this.rotation;
   }

   @Override
   public void bridge$setX(byte var1) {
      this.x = var1;
   }

   @Override
   public void bridge$setY(byte var1) {
      this.y = var1;
   }

   @Override
   public void bridge$setRot(byte var1) {
      this.rotation = var1;
   }

   @Override
   public Itemcounter$Type bridge$getDecorationType() {
      return switch (this.type) {
         case FRAME -> Itemcounter$Type.GREEN;
         case BLUE_MARKER -> Itemcounter$Type.BLUE;
         default -> null;
      };
   }
}
