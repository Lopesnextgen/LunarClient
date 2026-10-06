package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum DepthTestFunction implements GlEnum {
   LESS_DEPTH_TEST(513, true),
   EQUAL_DEPTH_TEST(514, true),
   LEQUAL_DEPTH_TEST(515, true),
   GREATER_DEPTH_TEST(516, true),
   NO_DEPTH_TEST(519, true),
   NEVER_DEPTH_TEST(512, true),
   NOTEQUAL_DEPTH_TEST(517, true),
   GEQUAL_DEPTH_TEST(518, true);

   private final int glId;
   private final boolean supported;

   public boolean isSupported() {
      return this.supported;
   }

   @Generated
   public int getGlId() {
      return this.glId;
   }

   @Generated
   DepthTestFunction(int value, boolean flag) {
      this.glId = value;
      this.supported = flag;
   }
}
