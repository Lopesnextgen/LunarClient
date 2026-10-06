package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum GlBlendFactor {
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

   public static GlBlendFactor fromId(int value) {
      for (GlBlendFactor bridgetype2_94 : values()) {
         if (bridgetype2_94.id == value) {
            return bridgetype2_94;
         }
      }

      return null;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   GlBlendFactor(int value) {
      this.id = value;
   }
}
