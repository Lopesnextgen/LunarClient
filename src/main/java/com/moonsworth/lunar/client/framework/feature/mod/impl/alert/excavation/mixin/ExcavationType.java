package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin;

import lombok.Generated;
import org.joml.Vector2i;

public enum ExcavationType {
   A(false, false, false),
   B(true, false, false),
   C(false, true, false),
   D(true, true, false),
   E(false, false, true),
   F(true, false, true),
   G(false, true, true),
   H(true, true, true);

   private final boolean swapXY;
   private final boolean flipX;
   private final boolean flipY;

   ExcavationType(boolean flag, boolean flag2, boolean flag3) {
      this.swapXY = flag;
      this.flipX = flag2;
      this.flipY = flag3;
   }

   public Vector2i transform(int value, int value2, int index3, int index4) {
      index3--;
      index4--;
      if (this.swapXY) {
         int number5 = value2;
         value2 = value;
         value = number5;
      }

      if (this.flipX) {
         value = index3 - value;
      }

      if (this.flipY) {
         value2 = index4 - value2;
      }

      return new Vector2i(value, value2);
   }

   @Generated
   public boolean isSwapXY() {
      return this.swapXY;
   }
}
