package com.moonsworth.lunar.bridge.slayer;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge5_12;
import java.util.Optional;

public interface Slayer2 {
   Slayer4 getConfig();

   Slayer3 getShaders();

   Optional<Slayer6> getCustomItems();

   Slayer8 getConnectedTextures();

   default Optional<Slayer5> getCustomColors() {
      return Optional.empty();
   }

   default int getBossTextColor(int var1) {
      return var1;
   }

   void setReloading(boolean var1);

   boolean isReloading();

   void waitOnAllChunksRendering(Bridge5_12 var1);

   void callSpriteUpdate(Bridge4_8 var1);

   void updateMultiTextureSprite(Bridge4_8 var1);
}
