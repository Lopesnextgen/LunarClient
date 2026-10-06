package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface ModelPlayerBridge extends ModelBipedBridge {
   @VersionGate(max = 25)
   ModelRendererBridge bridge$cloak();

   ModelRendererBridge bridge$leftSleeve();

   ModelRendererBridge bridge$rightSleeve();

   ModelRendererBridge bridge$leftPants();

   ModelRendererBridge bridge$rightPants();

   ModelRendererBridge bridge$jacket();

   boolean bridge$isSlim();

   @VersionGate(max = 0)
   boolean bridge$isMainModel();
}
