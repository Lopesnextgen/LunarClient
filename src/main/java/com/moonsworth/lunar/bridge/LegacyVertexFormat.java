package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class LegacyVertexFormat implements VertexFormatBridge {
   private final boolean hasUV;
   private final boolean hasColor;

   public LegacyVertexFormat(boolean flag, boolean flag2) {
      this.hasUV = flag;
      this.hasColor = flag2;
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
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof LegacyVertexFormat bridgetype2_62)) {
         return false;
      } else if (!bridgetype2_62.canEqual(this)) {
         return false;
      } else {
         return this.isHasUV() != bridgetype2_62.isHasUV() ? false : this.isHasColor() == bridgetype2_62.isHasColor();
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof LegacyVertexFormat;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.isHasUV() ? 79 : 97);
      return number2 * 59 + (this.isHasColor() ? 79 : 97);
   }
}
