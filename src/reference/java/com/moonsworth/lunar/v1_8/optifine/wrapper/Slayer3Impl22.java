package com.moonsworth.lunar.v1_8.optifine.wrapper;

import com.moonsworth.lunar.bridge.optifine.ConnectedProperties;
import com.moonsworth.lunar.bridge.optifine.ConnectedTexturesBridge;
import net.optifine.ConnectedTextures;

public class Slayer3Impl22 implements ConnectedTexturesBridge {
   public Slayer3Impl22() {
   }

   @Override
   public ConnectedProperties[][] getTileProperties() {
      return (ConnectedProperties[][])ConnectedTextures.tileProperties;
   }

   @Override
   public ConnectedProperties[][] getBlockProperties() {
      return (ConnectedProperties[][])ConnectedTextures.blockProperties;
   }
}
