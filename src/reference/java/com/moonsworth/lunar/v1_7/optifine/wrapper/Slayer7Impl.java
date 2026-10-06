package com.moonsworth.lunar.v1_7.optifine.wrapper;

import com.moonsworth.lunar.bridge.optifine.ConnectedProperties;
import com.moonsworth.lunar.bridge.optifine.ConnectedTexturesBridge;
import net.optifine.ConnectedTextures;

public class Slayer7Impl implements ConnectedTexturesBridge {
   public Slayer7Impl() {
   }

   public ConnectedProperties[][] getTileProperties() {
      return (ConnectedProperties[][])ConnectedTextures.tileProperties;
   }

   public ConnectedProperties[][] getBlockProperties() {
      return (ConnectedProperties[][])ConnectedTextures.blockProperties;
   }
}
