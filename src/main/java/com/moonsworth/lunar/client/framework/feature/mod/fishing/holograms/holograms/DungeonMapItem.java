package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.moonsworth.lunar.bridge.world.MapDataBridge;

public class DungeonMapItem {
   private final MapDataBridge field1;
   private final int field2;

   public DungeonMapItem(MapDataBridge itemcounter2_31, int value) {
      this.field1 = itemcounter2_31;
      this.field2 = value;
   }

   public MapDataBridge method1() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }
}
