package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Objects;

public interface EntityPositionApplier {
   default void method1(LocalPlayerContext nameplate31, double value, double value2, double value3, boolean flag) {
      Bridge5Extension_5 bridge5extension_59 = Objects.requireNonNull(Ref.method7());
      if (Ref.MC_VERSION >= 35) {
         Ref.method9().bridge$setClientLoaded(true);
      } else if (Ref.MC_VERSION >= 28) {
         bridge5extension_59.bridge$setClientLoaded(true);
      }

      bridge5extension_59.bridge$setPreviousPosX(nameplate31.getX());
      bridge5extension_59.bridge$setPreviousPosY(nameplate31.getY());
      bridge5extension_59.bridge$setPreviousPosZ(nameplate31.getZ());
      nameplate31.setPos(value, value2, value3);
      bridge5extension_59.bridge$lerpTo(value, value2, value3, nameplate31.getYaw(), nameplate31.getPitch());
      bridge5extension_59.bridge$setOnGround(flag);
      bridge5extension_59.bridge$calculateEntityAnimation();
   }
}
