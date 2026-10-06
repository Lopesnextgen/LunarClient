package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class BridgeType2_6 implements Bridge_63 {
   private final boolean hasUV;
   private final boolean hasColor;

   public BridgeType2_6(boolean var1, boolean var2) {
      this.hasUV = var1;
      this.hasColor = var2;
   }

   @Generated
   public boolean isHasUV() {
      return this.hasUV;
   }

   @Generated
   public boolean isHasColor() {
      return this.hasColor;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof BridgeType2_6 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else {
         return this.isHasUV() != var2.isHasUV() ? false : this.isHasColor() == var2.isHasColor();
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof BridgeType2_6;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.isHasUV() ? 79 : 97);
      return var2 * 59 + (this.isHasColor() ? 79 : 97);
   }
}
