package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum BridgeType2_9 {
   GL_ZERO(0),
   GL_ONE(1),
   GL_SRC_COLOR(768),
   GL_ONE_MINUS_SRC_COLOR(769),
   GL_DST_COLOR(774),
   GL_ONE_MINUS_DST_COLOR(775),
   GL_SRC_ALPHA(770),
   GL_ONE_MINUS_SRC_ALPHA(771),
   GL_DST_ALPHA(772),
   GL_ONE_MINUS_DST_ALPHA(773),
   GL_CONSTANT_COLOR(32769),
   GL_ONE_MINUS_CONSTANT_COLOR(32770),
   GL_CONSTANT_ALPHA(32771),
   GL_ONE_MINUS_CONSTANT_ALPHA(32772),
   GL_SRC_ALPHA_SATURATE(776),
   GL_SRC1_COLOR(35065),
   GL_ONE_MINUS_SRC1_COLOR(35066),
   GL_SRC1_ALPHA(34185),
   GL_ONE_MINUS_SRC1_ALPHA(35067);

   private final int id;

   public static BridgeType2_9 fromId(int value) {
      for (BridgeType2_9 var4 : values()) {
         if (var4.id == value) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   BridgeType2_9(int value) {
      this.id = value;
   }
}
