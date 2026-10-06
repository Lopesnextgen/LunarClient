package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;

public class SpacerWailaComponent implements WailaComponent {
   private final int field1;
   private final int field2;

   public SpacerWailaComponent(int number1) {
      this(number1, 0);
   }

   public SpacerWailaComponent(int number1, int value) {
      this.field1 = number1;
      this.field2 = value;
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
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int value, int value2) {
   }

   public int method2() {
      return this.field1;
   }

   public int method3() {
      return this.field2;
   }
}
