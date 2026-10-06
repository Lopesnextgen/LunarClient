package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import lombok.Generated;

public class SetRoomTypeAction extends RoomEvent {
   private final MapRoomType field2;
   private final MapRoomType field3;

   @Override
   public void method1(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().method15(this.field3);
   }

   @Override
   public void method2(DungeonRoomTracker holograms4iterator1) {
      holograms4iterator1.method30().method15(this.field2);
   }

   @Generated
   public SetRoomTypeAction(MapRoomType map, MapRoomType map2) {
      this.field2 = map;
      this.field3 = map2;
   }
}
