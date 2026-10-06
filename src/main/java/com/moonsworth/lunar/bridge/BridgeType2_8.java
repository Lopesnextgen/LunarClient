package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum BridgeType2_8 implements Bridge_23 {
   CONSTANT_ALPHA(32771, true),
   CONSTANT_COLOR(32769, true),
   DST_ALPHA(772, true),
   DST_COLOR(774, true),
   ONE(1, true),
   ONE_MINUS_CONSTANT_ALPHA(32772, true),
   ONE_MINUS_CONSTANT_COLOR(32770, true),
   ONE_MINUS_DST_ALPHA(773, true),
   ONE_MINUS_DST_COLOR(775, true),
   ONE_MINUS_SRC_ALPHA(771, true),
   ONE_MINUS_SRC_COLOR(769, true),
   SRC_ALPHA(770, true),
   SRC_ALPHA_SATURATE(776, true),
   SRC_COLOR(768, true),
   ZERO(0, true),
   SRC1_ALPHA(34185, false),
   SRC1_COLOR(35065, false),
   ONE_MINUS_SRC1_ALPHA(35067, false),
   ONE_MINUS_SRC1_COLOR(35066, false);

   private final int glId;
   private final boolean supported;

   public boolean isDestFactor() {
      return this.supported && this != SRC_ALPHA_SATURATE;
   }

   public boolean isSourceFactor() {
      return this.supported;
   }

   @Generated
   @Override
   public int getGlId() {
      return this.glId;
   }

   @Generated
   @Override
   public boolean isSupported() {
      return this.supported;
   }

   @Generated
   BridgeType2_8(int value, boolean flag) {
      this.glId = value;
      this.supported = flag;
   }
}
