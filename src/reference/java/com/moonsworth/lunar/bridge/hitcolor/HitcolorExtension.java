package com.moonsworth.lunar.bridge.hitcolor;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge_38;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import net.kyori.adventure.text.Component;

public interface HitcolorExtension extends Bridge_38 {
   @Override
   Vector3iBridge bridge$getBlockPos();

   Bridge3_23 bridge$getBlockType();

   @Override
   default Component bridge$getTypeName() {
      return this.bridge$getBlockType().bridge$getName();
   }

   @Annotation2(min = 8)
   default boolean bridge$canTurbo() {
      return true;
   }

   @Override
   default double bridge$getPosX() {
      return this.bridge$getBlockPos().bridge$getX() + 0.5;
   }

   @Override
   default double bridge$getPosY() {
      return this.bridge$getBlockPos().bridge$getY() + 0.5;
   }

   @Override
   default double bridge$getPosZ() {
      return this.bridge$getBlockPos().bridge$getZ() + 0.5;
   }

   @Annotation2(min = 8)
   default void bridge$triggerRebuild(boolean flag) {
      throw new AbstractMethodErrorImpl();
   }
}
