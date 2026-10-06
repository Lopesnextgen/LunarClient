package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import lombok.Generated;

public class SetRoomStateAction extends RoomEvent {
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState field2;
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState field3;

   @Override
   public void method1(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().method11(this.field3);
   }

   @Override
   public void method2(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().method11(this.field2);
   }

   @Generated
   public SetRoomStateAction(
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state2
   ) {
      this.field2 = state;
      this.field3 = state2;
   }
}
