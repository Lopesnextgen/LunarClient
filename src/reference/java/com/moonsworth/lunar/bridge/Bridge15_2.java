package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;

public interface Bridge15_2 {
   RenderTypeBridge bridge$getShinyPotion(int number1);

   RenderTypeBridge bridge$getSpriteItemType(boolean flag1);

   @VersionGate(min = 26)
   RenderTypeBridge bridge$getGuiTextured(ResourceLocationBridge horsestats141);

   BatchMultiBufferSourceBridge bridge$bufferSource();
}
