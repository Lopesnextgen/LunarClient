package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class RoomStateChange {
   private final long field1 = Ref.method3().bridge$getSystemTime();
   private final MapRoomType field2;
   private final MapRoomType field3;

   public RoomStateChange(MapRoomType map, MapRoomType map2) {
      this.field2 = map;
      this.field3 = map2;
   }

   public void method1(RoomStateHistory rewindhandlers1) {
      rewindhandlers1.method2(this.field3);
   }

   public void method2(RoomStateHistory rewindhandlers1) {
      rewindhandlers1.method2(this.field2);
   }

   @Generated
   public long getTimestamp() {
      return this.field1;
   }
}
