package com.moonsworth.lunar.client.render.color;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import lombok.Generated;
import org.jetbrains.annotations.Range;

public class AnimatedColorImpl implements AnimatedColor {
   private final int field1;
   private final ColorAnimation field2;
   private final int field3;

   @Override
   public boolean method14() {
      return true;
   }

   @Override
   public @Range(from = 1L, to = 100L) int method11() {
      return this.field3;
   }

   @Override
   public int method1(float value) {
      value = LcuiScreen.method151().getScaledWidth() + LcuiScreen.method151().getScaledHeight() - value;
      return this.field2.color().apply(value, this);
   }

   @Generated
   @Override
   public int getColor() {
      return this.field1;
   }

   @Generated
   public ColorAnimation method9() {
      return this.field2;
   }

   @Generated
   public int method13() {
      return this.field3;
   }

   @Generated
   AnimatedColorImpl(int value, ColorAnimation colorAnimation, int value2) {
      this.field1 = value;
      this.field2 = colorAnimation;
      this.field3 = value2;
   }
}
