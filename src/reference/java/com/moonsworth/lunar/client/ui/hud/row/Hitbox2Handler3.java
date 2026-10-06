package com.moonsworth.lunar.client.ui.hud.row;

import com.moonsworth.lunar.bridge.MixinHelper_4;

public class Hitbox2Handler3 implements Hitbox2 {
   private final Hitbox2[] field1;
   private final float field2;
   private final float field3;
   public final int padding;

   public Hitbox2Handler3(Hitbox2[] var1, int var2) {
      this.field1 = var1;
      this.padding = var2;
      float var3 = 0.0F;
      float var4 = 0.0F;

      for (Hitbox2 var8 : var1) {
         var4 += var8.method2();
         if (var8.method3() > var3) {
            var3 = var8.method3();
         }
      }

      this.field2 = var4 + var2;
      this.field3 = var3;
   }

   @Override
   public void method1(MixinHelper_4 var1, float var2, float var3) {
      for (Hitbox2 var7 : this.field1) {
         var7.method1(var1, var2, var3 + (this.field3 - var7.method3()) / 2.0F);
         var2 += var7.method2();
      }
   }

   @Override
   public float method2() {
      return this.field2;
   }

   @Override
   public float method3() {
      return this.field3;
   }
}
