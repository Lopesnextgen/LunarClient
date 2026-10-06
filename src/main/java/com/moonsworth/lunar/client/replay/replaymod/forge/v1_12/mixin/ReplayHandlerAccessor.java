package com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin;

public interface ReplayHandlerAccessor {
   void bridge$setStopped(boolean flag1);

   void bridge$setPaused(boolean flag1);

   void bridge$updateState();
}
