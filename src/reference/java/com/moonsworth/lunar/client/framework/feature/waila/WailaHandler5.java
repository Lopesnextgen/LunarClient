package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;

public class WailaHandler5 implements Waila {
   private final int field1;
   private final int field2;

   public WailaHandler5(int var1) {
      this(var1, 0);
   }

   public WailaHandler5(int var1, int var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public int getWidth() {
      return this.field1;
   }

   @Override
   public int getHeight() {
      return this.field2;
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int value, int value2) {
   }

   public int method2() {
      return this.field1;
   }

   public int method3() {
      return this.field2;
   }
}
