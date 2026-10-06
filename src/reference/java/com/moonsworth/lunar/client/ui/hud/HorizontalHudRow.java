package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.MixinHelper_4;

public class HorizontalHudRow implements HudRow {
   private final HudRow[] field1;
   private final float field2;
   private final float field3;
   public final int padding;

   public HorizontalHudRow(HudRow[] items1, int value) {
      this.field1 = items1;
      this.padding = value;
      float value3 = 0.0F;
      float value4 = 0.0F;

      for (HudRow hitbox28 : items1) {
         value4 += hitbox28.method2();
         if (hitbox28.method3() > value3) {
            value3 = hitbox28.method3();
         }
      }

      this.field2 = value4 + value;
      this.field3 = value3;
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, float value, float value3) {
      for (HudRow hitbox27 : this.field1) {
         hitbox27.method1(mixinhelper_41, value, value3 + (this.field3 - hitbox27.method3()) / 2.0F);
         value += hitbox27.method2();
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
