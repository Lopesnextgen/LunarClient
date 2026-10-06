package com.moonsworth.lunar.bridge.tileentity;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.TurboBlockBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.kyori.adventure.text.Component;

public interface BlockEntityBridge extends TurboBlockBridge {
   Vec3iBridge bridge$getBlockPos();

   Bridge3_23 bridge$getBlockType();

   default Component bridge$getTypeName() {
      return this.bridge$getBlockType().bridge$getName();
   }

   @VersionGate(min = 8)
   default boolean bridge$canTurbo() {
      return true;
   }

   default double bridge$getPosX() {
      return this.bridge$getBlockPos().bridge$getX() + 0.5;
   }

   default double bridge$getPosY() {
      return this.bridge$getBlockPos().bridge$getY() + 0.5;
   }

   default double bridge$getPosZ() {
      return this.bridge$getBlockPos().bridge$getZ() + 0.5;
   }

   @VersionGate(min = 8)
   default void bridge$triggerRebuild(boolean flag) {
      throw new AbstractMethodErrorImpl();
   }
}
