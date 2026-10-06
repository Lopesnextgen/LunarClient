package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.slayer.Slayer;
import com.moonsworth.lunar.bridge.slayer.Slayer8;
import net.optifine.ConnectedTextures;

public class Slayer3Renderer2 implements Slayer8 {
   public Slayer[][] getTileProperties() {
      return (Slayer[][])ConnectedTextures.tileProperties;
   }

   public Slayer[][] getBlockProperties() {
      return (Slayer[][])ConnectedTextures.blockProperties;
   }
}
