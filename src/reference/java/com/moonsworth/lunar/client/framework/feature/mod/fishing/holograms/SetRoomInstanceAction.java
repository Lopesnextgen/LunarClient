package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import lombok.Generated;

public class SetRoomInstanceAction extends RoomEvent {
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance field2;
   private final com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance field3;

   @Override
   public void method1(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method2(this.field3);
   }

   @Override
   public void method2(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method2(this.field2);
   }

   @Generated
   public SetRoomInstanceAction(
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms31,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms32
   ) {
      this.field2 = holograms31;
      this.field3 = holograms32;
   }
}
