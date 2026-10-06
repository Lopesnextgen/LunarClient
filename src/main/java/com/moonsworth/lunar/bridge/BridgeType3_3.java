package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum BridgeType3_3 {
   GL_MODELVIEW(5888),
   GL_PROJECTION(5889),
   GL_TEXTURE(5890),
   GL_COLOR(6144);

   private final int id;

   public static BridgeType3_3 fromId(int value) {
      for (BridgeType3_3 var4 : values()) {
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
   BridgeType3_3(int value) {
      this.id = value;
   }
}
