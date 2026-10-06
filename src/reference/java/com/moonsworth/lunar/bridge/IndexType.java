package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import lombok.Generated;

public enum IndexType {
   BYTE(1),
   SHORT(2),
   INT(4);

   private final int bytes;

   public static IndexType least(int value) {
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
   IndexType(int value) {
      this.bytes = value;
   }
}
