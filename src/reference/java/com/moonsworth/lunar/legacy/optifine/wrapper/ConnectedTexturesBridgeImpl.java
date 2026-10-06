package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.optifine.ConnectedProperties;
import com.moonsworth.lunar.bridge.optifine.ConnectedTexturesBridge;
import net.optifine.ConnectedTextures;

public class ConnectedTexturesBridgeImpl implements ConnectedTexturesBridge {
   public ConnectedTexturesBridgeImpl() {
   }

   public ConnectedProperties[][] getTileProperties() {
      return (ConnectedProperties[][])ConnectedTextures.tileProperties;
   }

   public ConnectedProperties[][] getBlockProperties() {
      return (ConnectedProperties[][])ConnectedTextures.blockProperties;
   }
}
