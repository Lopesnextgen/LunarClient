package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;

class RouteSectionEntity {
   private final BridgeExtension field1;
   private final RouteSection field2;

   private RouteSectionEntity(BridgeExtension bridge, RouteSection holograms72) {
      this.field1 = bridge;
      this.field2 = holograms72;
   }

   public BridgeExtension method1() {
      return this.field1;
   }

   public RouteSection method2() {
      return this.field2;
   }
}
