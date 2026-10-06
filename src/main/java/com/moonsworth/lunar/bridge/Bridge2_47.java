package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public interface Bridge2_47 {
   void bridge$setPlayingSoundVolume(ResourceLocationBridge var1, float var2);

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   void bridge$setReloadingBlocking(boolean var1);

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   boolean bridge$isReloadingBlocking();

   void bridge$pause();

   void bridge$resume();
}
