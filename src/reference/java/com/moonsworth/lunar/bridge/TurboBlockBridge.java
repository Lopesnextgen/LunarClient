package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.kyori.adventure.text.Component;

public interface TurboBlockBridge {
   @VersionGate(min = 8)
   default boolean lunar$supportsTurbo() {
      return false;
   }

   @VersionGate(min = 8)
   default float bridge$minimumTurboDistance() {
      return 0.0F;
   }

   Component bridge$getTypeName();

   @VersionGate(min = 8)
   default boolean bridge$isTurbo() {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 8)
   default void bridge$setTurbo(boolean flag) {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 8)
   default void bridge$stopTurbo() {
      this.bridge$setTurbo(false);
   }

   double bridge$getPosX();

   double bridge$getPosY();

   double bridge$getPosZ();

   default Vec3iBridge bridge$getBlockPos() {
      return Vec3iBridge.field1;
   }

   default AxisAlignedBBBridge bridge$getBoundingBox() {
      return AxisAlignedBBBridge.method18();
   }
}
