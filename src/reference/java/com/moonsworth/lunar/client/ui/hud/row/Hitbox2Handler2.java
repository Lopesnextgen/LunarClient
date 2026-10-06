package com.moonsworth.lunar.client.ui.hud.row;

import com.moonsworth.lunar.bridge.MixinHelper_4;

public class Hitbox2Handler2 implements Hitbox2 {
   private final int field1;

   public Hitbox2Handler2(int var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(MixinHelper_4 var1, float value, float value2) {
   }

   @Override
   public float method2() {
      return this.field1;
   }

   @Override
   public float method3() {
      return 10.0F;
   }
}
