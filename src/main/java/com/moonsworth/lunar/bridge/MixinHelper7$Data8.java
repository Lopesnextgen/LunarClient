package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class MixinHelper7$Data8 {
   private final float lineWidth;
   private final boolean isDefault;

   public static MixinHelper7$Data8 method1() {
      return new MixinHelper7$Data8(1.0F, true);
   }

   public static MixinHelper7$Data8 method2(float value) {
      return new MixinHelper7$Data8(value, false);
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
   private MixinHelper7$Data8(float value, boolean flag) {
      this.lineWidth = value;
      this.isDefault = flag;
   }
}
