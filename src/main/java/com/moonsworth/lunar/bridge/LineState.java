package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class LineState {
   private final float lineWidth;
   private final boolean isDefault;

   public static LineState method1() {
      return new LineState(1.0F, true);
   }

   public static LineState method2(float value) {
      return new LineState(value, false);
   }

   @Generated
   public float method3() {
      return this.lineWidth;
   }

   @Generated
   public boolean isDefault() {
      return this.isDefault;
   }

   @Generated
   private LineState(float value, boolean flag) {
      this.lineWidth = value;
      this.isDefault = flag;
   }
}
