package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 15)
public interface Bridge9_6 {
   default void bridge$setShouldAlwaysAnimate(boolean flag) {
      throw new AbstractMethodErrorImpl();
   }

   default boolean bridge$shouldAlwaysAnimate() {
      throw new AbstractMethodErrorImpl();
   }
}
