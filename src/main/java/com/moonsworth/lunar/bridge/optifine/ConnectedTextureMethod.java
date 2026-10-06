package com.moonsworth.lunar.bridge.optifine;

public enum ConnectedTextureMethod {
   NONE,
   CTM,
   HORIZONTAL,
   TOP,
   RANDOM,
   REPEAT,
   VERTICAL,
   FIXED,
   HORIZONTAL_VERTICAL,
   VERTICAL_HORIZONTAL,
   CTM_COMPACT,
   OVERLAY,
   OVERLAY_FIXED,
   OVERLAY_RANDOM,
   OVERLAY_REPEAT,
   OVERLAY_CTM;

   public static final ConnectedTextureMethod[] VALUES = values();

   ConnectedTextureMethod() {
   }
}
