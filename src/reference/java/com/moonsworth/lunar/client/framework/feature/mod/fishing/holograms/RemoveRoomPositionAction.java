package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import lombok.Generated;

public class RemoveRoomPositionAction extends RoomEvent {
   private final WorldPosition field2;

   @Override
   public void method1(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method28().remove(this.field2);
   }

   @Override
   public void method2(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method28().add(this.field2);
   }

   @Generated
   public RemoveRoomPositionAction(WorldPosition nameplate41) {
      this.field2 = nameplate41;
   }
}
