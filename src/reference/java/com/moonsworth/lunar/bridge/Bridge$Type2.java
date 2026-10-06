package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import lombok.Generated;

public enum Bridge$Type2 {
   BYTE(1),
   SHORT(2),
   INT(4);

   private final int bytes;

   public static Bridge$Type2 least(int value) {
      if ((value & -65536) != 0) {
         return INT;
      } else {
         return Bridge.getMinecraftVersion().method4(Config.field18) ? SHORT : ((value & 0xFF00) != 0 ? SHORT : BYTE);
      }
   }

   @Generated
   public int getBytes() {
      return this.bytes;
   }

   @Generated
   Bridge$Type2(int value) {
      this.bytes = value;
   }
}
