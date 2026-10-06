package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import org.joml.Vector3d;
import org.joml.Vector3i;

public interface PlayerControllerBridge {
   boolean bridge$isSpectator();

   int bridge$destroyDelay();

   void bridge$attack();

   void bridge$useItemOn(Vector3i vector3i1, int number2, int number3, Vector3d vector3d4, boolean flag5, boolean flag6);

   void bridge$useItem(int number1);

   Horsestats20Extension2 bridge$getBlockBeingDestroyed();

   float bridge$getBlockDestroyProgress();

   boolean bridge$isHittingBlock();
}
