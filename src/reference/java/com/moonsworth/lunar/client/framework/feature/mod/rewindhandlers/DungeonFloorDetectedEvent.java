package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(DungeonFloorListener.class)
public class DungeonFloorDetectedEvent extends LunarEvent implements DynamicListenerEvent {
   private final DungeonFloor field1;

   @Generated
   public DungeonFloor method1() {
      return this.field1;
   }

   @Generated
   public DungeonFloorDetectedEvent(DungeonFloor dungeonFloor) {
      this.field1 = dungeonFloor;
   }
}
