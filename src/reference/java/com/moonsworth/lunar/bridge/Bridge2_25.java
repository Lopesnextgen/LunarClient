package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import org.joml.Vector3d;
import org.joml.Vector3i;

public interface Bridge2_25 {
   boolean bridge$isSpectator();

   int bridge$destroyDelay();

   void bridge$attack();

   void bridge$useItemOn(Vector3i var1, int var2, int var3, Vector3d var4, boolean var5, boolean var6);

   void bridge$useItem(int var1);

   Horsestats20Extension2 bridge$getBlockBeingDestroyed();

   float bridge$getBlockDestroyProgress();

   boolean bridge$isHittingBlock();
}
