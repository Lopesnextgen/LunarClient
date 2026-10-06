package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class DungeonClassChangedEvent extends LunarEvent {
   private final DungeonPlayerTracker field1;
   private final DungeonClass field2;

   @Generated
   public DungeonPlayerTracker method1() {
      return this.field1;
   }

   @Generated
   public DungeonClass method2() {
      return this.field2;
   }

   @Generated
   public DungeonClassChangedEvent(DungeonPlayerTracker holograms4updater1, DungeonClass hologramstype2_22) {
      this.field1 = holograms4updater1;
      this.field2 = hologramstype2_22;
   }
}
