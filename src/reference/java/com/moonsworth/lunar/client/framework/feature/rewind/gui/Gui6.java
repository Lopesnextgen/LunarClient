package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;

public interface Gui6 {
   default void method1(Nameplate3 nameplate3, double value, double value2, double value3, boolean flag) {
      Bridge5Extension_5 var9 = Objects.requireNonNull(ThreadModuleDump63.method7());
      if (ThreadModuleDump63.MC_VERSION >= 35) {
         ThreadModuleDump63.method9().bridge$setClientLoaded(true);
      } else if (ThreadModuleDump63.MC_VERSION >= 28) {
         var9.bridge$setClientLoaded(true);
      }

      var9.bridge$setPreviousPosX(nameplate3.getX());
      var9.bridge$setPreviousPosY(nameplate3.getY());
      var9.bridge$setPreviousPosZ(nameplate3.getZ());
      nameplate3.setPos(value, value2, value3);
      var9.bridge$lerpTo(value, value2, value3, nameplate3.getYaw(), nameplate3.getPitch());
      var9.bridge$setOnGround(flag);
      var9.bridge$calculateEntityAnimation();
   }
}
