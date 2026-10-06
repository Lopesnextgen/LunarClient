package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;

public interface SoundManagerBridge {
   void bridge$setPlayingSoundVolume(ResourceLocationBridge horsestats141, float value2);

   @VersionGate(max = 5)
   void bridge$setReloadingBlocking(boolean flag1);

   @VersionGate(max = 5)
   boolean bridge$isReloadingBlocking();

   void bridge$pause();

   void bridge$resume();
}
