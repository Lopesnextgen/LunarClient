package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter$Type;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_2;
import net.minecraft.util.Vec4b;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vec4b.class)
public class Vec4bMixin implements Itemcounter_2 {
   @Shadow
   public byte field_176115_b;
   @Shadow
   public byte field_176116_c;
   @Shadow
   public byte field_176114_d;
   @Shadow
   public byte field_176117_a;

   @Override
   public byte bridge$getX() {
      return this.field_176115_b;
   }

   @Override
   public byte bridge$getY() {
      return this.field_176116_c;
   }

   @Override
   public byte bridge$getRot() {
      return this.field_176114_d;
   }

   @Override
   public void bridge$setX(byte var1) {
      this.field_176115_b = var1;
   }

   @Override
   public void bridge$setY(byte var1) {
      this.field_176116_c = var1;
   }

   @Override
   public void bridge$setRot(byte var1) {
      this.field_176114_d = var1;
   }

   @Override
   public Itemcounter$Type bridge$getDecorationType() {
      return Itemcounter$Type.fromLegacyId(this.field_176117_a);
   }
}
