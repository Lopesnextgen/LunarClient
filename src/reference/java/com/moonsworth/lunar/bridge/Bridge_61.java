package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import org.joml.Math;

public interface Bridge_61 {
   double bridge$getPosX();

   double bridge$getPosY();

   double bridge$getPosZ();

   double bridge$getMotionX();

   double bridge$getMotionY();

   double bridge$getMotionZ();

   float bridge$ageInTicks();

   float bridge$getWidth();

   float bridge$getHeight();

   float bridge$getEyeHeight();

   double bridge$distanceToCameraSq();

   boolean bridge$isInvisible();

   boolean bridge$isVisiblyCrouching();

   boolean bridge$isDiscrete();

   boolean bridge$isOnFire();

   int bridge$getEntityId();

   default int bridge$getBlockX() {
      return (int)Math.floor(this.bridge$getPosX());
   }

   default int bridge$getBlockY() {
      return (int)Math.floor(this.bridge$getPosY());
   }

   default int bridge$getBlockZ() {
      return (int)Math.floor(this.bridge$getPosZ());
   }

   default Horsestats20Extension method1() {
      return Horsestats20Extension.method4(this);
   }

   @Nullable
   Vec3Bridge bridge$getPassengerOffset(@Nullable BridgeExtension var1);

   @Nullable
   Component bridge$getCustomName();

   @Nullable
   default Component bridge$getDisplayNameTag() {
      return this.bridge$getCustomName();
   }

   @Nullable
   Vec3Bridge bridge$getNameTagAttachment();

   default void bridge$extractRenderStates(BridgeExtension var1) {
   }

   void bridge$extractRenderStates$Entity(BridgeExtension var1);

   default int bridge$getOutlineColor() {
      return -1;
   }
}
