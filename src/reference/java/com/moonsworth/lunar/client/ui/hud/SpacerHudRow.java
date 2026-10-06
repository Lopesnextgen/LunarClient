package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.MixinHelper_4;

public class SpacerHudRow implements HudRow {
   private final int field1;

   public SpacerHudRow(int value) {
      this.field1 = value;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, float value, float value2) {
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
