package com.moonsworth.lunar.client.tps;

public enum TpsType {
   COLLECTING,
   RENDERING_OPAQUE,
   RENDERING_TRANSLUCENT,
   POST_RENDER;

   public boolean isBefore(TpsType var1) {
      return this.ordinal() < var1.ordinal();
   }

   public boolean isAfter(TpsType var1) {
      return this.ordinal() > var1.ordinal();
   }
}
