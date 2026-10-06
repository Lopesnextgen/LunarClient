package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import net.kyori.adventure.text.Component;

public interface Bridge_38 {
   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default boolean lunar$supportsTurbo() {
      return false;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default float bridge$minimumTurboDistance() {
      return 0.0F;
   }

   Component bridge$getTypeName();

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default boolean bridge$isTurbo() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$setTurbo(boolean flag) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$stopTurbo() {
      this.bridge$setTurbo(false);
   }

   double bridge$getPosX();

   double bridge$getPosY();

   double bridge$getPosZ();

   default Vector3iBridge bridge$getBlockPos() {
      return Vector3iBridge.field1;
   }

   default AxisAlignedBBBridge bridge$getBoundingBox() {
      return AxisAlignedBBBridge.method18();
   }
}
