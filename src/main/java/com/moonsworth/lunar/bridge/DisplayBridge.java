package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface DisplayBridge {
   long bridge$getDisplayHandle();

   long bridge$getWindowHandle();

   @VersionGate(max = 5)
   default void bridge$toggleRawInput(boolean flag) {
   }
}
