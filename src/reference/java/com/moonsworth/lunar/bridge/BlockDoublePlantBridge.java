package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(max = 5)
public interface BlockDoublePlantBridge {
   default boolean bridge$isSunflower(BlockStateBridge bridge2_171) {
      return false;
   }

   default boolean bridge$isRoseBush(BlockStateBridge bridge2_171) {
      return false;
   }
}
