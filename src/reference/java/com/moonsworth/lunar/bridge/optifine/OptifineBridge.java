package com.moonsworth.lunar.bridge.optifine;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import java.util.Optional;

public interface OptifineBridge {
   OptifineConfigBridge getConfig();

   ShadersBridge getShaders();

   Optional<CustomItemsBridge> getCustomItems();

   ConnectedTexturesBridge getConnectedTextures();

   default Optional<CustomColorsBridge> getCustomColors() {
      return Optional.empty();
   }

   default int getBossTextColor(int value) {
      return value;
   }

   void setReloading(boolean flag1);

   boolean isReloading();

   void waitOnAllChunksRendering(MinecraftBridge bridge5_121);

   void callSpriteUpdate(Bridge4_8 bridge4_81);

   void updateMultiTextureSprite(Bridge4_8 bridge4_81);
}
